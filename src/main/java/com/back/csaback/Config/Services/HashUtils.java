package com.back.csaback.Config.Services;

import org.springframework.stereotype.Service;

public class HashUtils {
    public static boolean isValidMD5(String hash) {
        return hash != null && hash.matches("^[a-fA-F0-9]{32}$");
    }
}