package com.titiksha.tickets.config;

import com.titiksha.tickets.filters.UserProvisioningFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserProvisioningFilter userProvisioningFilter) throws Exception {
        http
            .authorizeHttpRequests(authorize -> 
                authorize.anyRequest().authenticated()
            )
            .csrf(csrf->csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .oauth2ResourceServer(oauth->oauth.jwt(
                Customizer.withDefaults()
            ))
            .addFilterBefore(userProvisioningFilter, BearerTokenAuthenticationFilter.class);
        return http.build();
    }

    // Simple JwtDecoder for local development/testing when Keycloak auto-config isn't available.
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] secret = "dev-secret-key-should-be-changed".getBytes(StandardCharsets.UTF_8);
        SecretKeySpec key = new SecretKeySpec(secret, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
            


            /* .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();*/

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

}
