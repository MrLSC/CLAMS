package com.winconlab.clams.utils;

import java.util.UUID;

public class IdUtil {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
