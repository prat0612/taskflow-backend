package com.taskflow.util;

import org.slf4j.MDC;

public class LoggingUtil {

    private static final String USER_KEY = "user";

    public static void setUser(String email) {
        if (email != null) {
            MDC.put(USER_KEY, email);
        }
    }

    public static void clear() {
        MDC.clear();
    }
}
