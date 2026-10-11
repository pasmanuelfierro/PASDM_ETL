package com.pasdm.integration.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public static String calculateRowHash(String fecha, String status, String estructura, String lugar, String param4, String param5, String param6,String param7) {

        String raw = String.join("|",
                fecha,
                status,
                estructura,
                lugar,
                param4,
                param5,
                param6,
                param7
        );

        return DigestUtils.sha256Hex(raw);
    }
}
