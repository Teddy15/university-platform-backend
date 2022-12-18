package com.uni.platform.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    private SecurityUtils() {}
    public static UserDetails getUserDetails() {
        return (UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
