package com.mslfox.oniipswingjpamvc.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPv4Validator {

    public static final String IPV4_PATTERN = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);


    public static boolean isValidIPv4(String ipAddress) {
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

}