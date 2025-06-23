package com.fst.Jupitech.enums;

import org.springframework.security.core.GrantedAuthority;
public enum RolesEnum implements GrantedAuthority {
        ADMIN("ADMIN"),
        SELLER("SELLER"),
        CLIENT("CLIENT");

        private final String authority;

        RolesEnum(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return this.name();
        }
    
}
