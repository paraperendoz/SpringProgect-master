package com.levelup.levelup.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, MENTOR, ADMIN;

    @Override
    public  String getAuthority() {
        return name();
    }
}
