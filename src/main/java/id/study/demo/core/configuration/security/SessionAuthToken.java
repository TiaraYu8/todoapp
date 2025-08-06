package id.study.demo.core.configuration.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class SessionAuthToken extends AbstractAuthenticationToken {
    private final String sessionToken;
    private final Object principal;

    public SessionAuthToken(Object principal, String sessionToken) {
        super(null);
        this.principal = principal;
        this.sessionToken = sessionToken;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return sessionToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
