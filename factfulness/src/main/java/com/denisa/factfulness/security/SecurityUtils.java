package com.denisa.factfulness.security;

import liquibase.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static ApplicationUserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        final Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        if (principal instanceof ApplicationUserDetails && (StringUtils.isNotEmpty(((ApplicationUserDetails) principal).getUsername()))) {
            return (ApplicationUserDetails) principal;
        }
        return null;
    }
}
