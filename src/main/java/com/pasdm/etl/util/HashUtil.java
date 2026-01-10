package com.pasdm.etl.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public static String calculateRowHash(String fecha, String status, String estructura, String lugar) {

        String raw = String.join("|",
                fecha,
                status,
                estructura,
                lugar
        );

        return DigestUtils.sha256Hex(raw);
    }
}
