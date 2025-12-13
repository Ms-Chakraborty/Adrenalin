package com.titiksha.tickets.config;

import com.titiksha.tickets.filters.UserProvisioningFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.config.Customizer;
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
    }

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
