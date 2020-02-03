package com.denisa.factfulness.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApplicationAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ApplicationPasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    public ApplicationAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String authUsername = authentication.getName();
        final String authPassword = authentication.getCredentials().toString();
        ApplicationUserDetails principal = userDetailsService.loadUserByUsername(authUsername);
        if (authUsername.equals(principal.getUsername()) && passwordEncoder.matches(authPassword, principal.getPassword())) {
            return new UsernamePasswordAuthenticationToken(principal, authPassword, principal.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}