package net.lelyak.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Nazar Lelyak.
 */
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
