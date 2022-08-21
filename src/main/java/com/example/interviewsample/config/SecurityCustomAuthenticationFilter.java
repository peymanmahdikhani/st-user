package com.example.interviewsample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Peyman Mahdikhani on 8/20/2022.
 * email: payman.mahdikhani@gmail.com
 * Url: www.linkedin.com/in/peyman-mahdikhani
 * workspace
 */
@Slf4j
public class SecurityCustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected SecurityCustomAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = getUserName(request);
        String secret = getPassword(request);

        if (secret == null || username == null) {
            throw new UsernameNotFoundException("Oops!");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                secret
        );


        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }




    private String getPassword(HttpServletRequest request) {
        return request.getHeader("Api-Secret");
    }

    private String getUserName(HttpServletRequest request) {
        return request.getHeader("Api-Key");
    }
}
