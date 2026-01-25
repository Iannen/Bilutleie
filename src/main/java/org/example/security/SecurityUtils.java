package org.example.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;



public class SecurityUtils {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2A);

    public static String encodePassword(String rawPassword,String salt) {
        return passwordEncoder.encode(rawPassword + salt);
    }
    public static boolean matches(String enteredPassword, String storedHashedPassword, String salt) {
        String combinedPassword = enteredPassword + salt;
        return passwordEncoder.matches(combinedPassword, storedHashedPassword);
    }
}
