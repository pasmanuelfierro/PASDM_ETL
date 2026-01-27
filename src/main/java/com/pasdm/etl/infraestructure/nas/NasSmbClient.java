package com.pasdm.etl.infraestructure.nas;

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

    public NasSmbClient(
            @Value("${nas.domain}") String domain,
            @Value("${nas.user}") String user,
            @Value("${nas.password}") String password,
            @Value("${nas.password.security}") String passwordSecurity
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
    }

    public InputStream openFile(String smbPath) throws Exception {

        if (smbPath.contains("Estadisticos - SSO - MLC")) {
            SmbFile file = new SmbFile(smbPath, authSecurityContext);
            return file.getInputStream();
        } else {
            SmbFile file = new SmbFile(smbPath, authContext);
            return file.getInputStream();
        }
    }
}