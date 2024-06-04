package com.nmb.sportwear_store.security;

import com.nmb.sportwear_store.exception.UserBlockedException;
import com.nmb.sportwear_store.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private LoginAttemptService loginAttemptService;

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (loginAttemptService.isBlocked(username)) {
            throw new BadCredentialsException("Account is locked due to multiple failed login attempts.");
        }

        try {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetailsService.loadUserByUsername(username), password, userDetailsService.loadUserByUsername(username).getAuthorities()
            );

            loginAttemptService.loginSucceeded(username);
            return authenticationToken;
        } catch (AuthenticationException e) {
            loginAttemptService.loginFailed(username);
            throw e;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
