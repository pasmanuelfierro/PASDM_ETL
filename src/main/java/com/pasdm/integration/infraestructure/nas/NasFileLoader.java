package com.pasdm.integration.infraestructure.nas;

import jcifs.CIFSContext;
import jcifs.config.PropertyConfiguration;
import jcifs.context.BaseContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;

import java.io.InputStream;
import java.util.Properties;

public class NasFileLoader {

    public static InputStream openExcel(String path) throws Exception {

        Properties props = new Properties();
        props.put("jcifs.smb.client.disableSMB1", "true");
        props.put("jcifs.smb.client.dfs.disabled", "true");

        CIFSContext baseContext =
                new BaseContext(new PropertyConfiguration(props));

        CIFSContext authContext = baseContext.withCredentials(
                new NtlmPasswordAuthenticator(
                        "",   // puede ser ""
                        "mfierro",
                        "Lcmine2026"
                )
        );

        String smbPath2 = "smb://172.30.1.24/compartida%20temporal/LC_DIARIO/reporte%20gerencia%20desarrollo.xlsx";

        String smbPath = "smb:" + path;

        SmbFile smbFile = new SmbFile(smbPath, authContext);

        if (!smbFile.exists()) {
            throw new IllegalStateException("Archivo no existe en NAS");
        }

        return smbFile.getInputStream();
    }
}