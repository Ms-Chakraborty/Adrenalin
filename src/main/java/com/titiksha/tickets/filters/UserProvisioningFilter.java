package com.titiksha.tickets.filters;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.oauth2.jwt.Jwt;

import com.titiksha.tickets.domain.User;
import com.titiksha.tickets.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(UserProvisioningFilter.class);

    private final UserRepository userRepository;

    public UserProvisioningFilter() {
        this.userRepository = null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            try {
                String subject = jwt.getSubject();
                if (subject != null) {
                    UUID keycloak = UUID.fromString(subject);
                    if (!userRepository.existsById(keycloak)) {
                        User user = new User();
                        user.setId(keycloak);
                        Object preferred = jwt.getClaims().get("preferred_username");
                        if (preferred != null) {
                            user.setName(preferred.toString());
                        }
                        Object email = jwt.getClaims().get("email");
                        if (email != null) {
                            user.setEmail(email.toString());
                        }
                        userRepository.save(user);
                        logger.debug("Provisioned new user with id {}", keycloak);
                    }
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid UUID in JWT subject: {}", jwt.getSubject(), e);
            } catch (Exception e) {
                logger.error("Error provisioning user from JWT", e);
            }
        }

        filterChain.doFilter(request, response);
    }

}/* 
package com.titiksha.tickets.filters;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jose.proc.SecurityContext;
import com.titiksha.tickets.domain.User;
import com.titiksha.tickets.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // filter logic here
        filterChain.doFilter(request, response);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthentication() && authentication.getPrincipal() instance of )Jwt = null jwt
{
            
            UUID keycloak=UUID.fromString(jwt.getSubject());

            if(!userRepository.existsById(keycloak)){
                User user=new User();
                user.setId(keycloak);

                user.setName(jwt.getClaimAsString("preferred_username"));

                user.setEmail(jwt.getClaimAsString("email"));
                userRepository.save(user);
            }



        }
        filterChain.doFilter(request, response);
    }

}*/
