package com.pasdm.integration.infraestructure.nas;

import jcifs.CIFSContext;
import jcifs.config.PropertyConfiguration;
import jcifs.context.BaseContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class NasSmbClient {

    private final CIFSContext authContext;
    private final CIFSContext authSecurityContext;
    private final CIFSContext authGeologyContext;
    private final CIFSContext authDailyCmdContext;

    @Value("${excel.geology.grade.path}")
    private String excelGeologyGradePath;

    @Value("${excel.geology.path}")
    private String excelGeologyPath;

    @Value("${excel.daily.cmd.actual.path}")
    private String excelDailytCMDActualPath;

    public NasSmbClient(
            @Value("${nas.domain}") String domain,
            @Value("${nas.user}") String user,
            @Value("${nas.password}") String password,
            @Value("${nas.password.security}") String passwordSecurity,
            @Value("${nas.password.geology}") String passwordGeology,
            @Value("${nas.user.geology}") String userGeology,
            @Value("${nas.domain.user}") String domainUser,
            @Value("${nas.domain.password}") String domainPassword

    ) throws Exception {

        Properties props = new Properties();
        props.put("jcifs.smb.client.disableSMB1", "true");
        props.put("jcifs.smb.client.responseTimeout", "60000");
        props.put("jcifs.smb.client.connTimeout", "60000");
        props.put("jcifs.smb.client.dfs.disabled", "true");

        CIFSContext base = new BaseContext(new PropertyConfiguration(props));

        this.authContext = base.withCredentials(
                new NtlmPasswordAuthenticator(null, user, password)
        );

        this.authSecurityContext = base.withCredentials(
                new NtlmPasswordAuthenticator(null, user, passwordSecurity)
        );

        this.authGeologyContext = base.withCredentials(
                new NtlmPasswordAuthenticator(null, userGeology, passwordGeology)
        );
        
        this.authDailyCmdContext = base.withCredentials(
                new NtlmPasswordAuthenticator(null, domainUser, domainPassword)
        );
    }

    public InputStream openFile(String smbPath) throws Exception {

        if (smbPath.contains("Estadisticos - SSO - MLC")) {
            SmbFile file = new SmbFile(smbPath, authSecurityContext);
            return file.getInputStream();
        } else if (smbPath.equals(excelGeologyPath) || smbPath.equals(excelGeologyGradePath)) {
            SmbFile file = new SmbFile(smbPath, authGeologyContext);
            return file.getInputStream();
        } else if (smbPath.equals(excelDailytCMDActualPath)) {
            SmbFile file = new SmbFile(smbPath, authDailyCmdContext);
            return file.getInputStream();
        } else {
            SmbFile file = new SmbFile(smbPath, authContext);
            return file.getInputStream();
        }
    }
}