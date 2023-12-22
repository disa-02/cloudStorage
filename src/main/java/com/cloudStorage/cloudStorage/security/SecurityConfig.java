package com.cloudStorage.cloudStorage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloudStorage.cloudStorage.security.filter.AuthenticationFilter;
import com.cloudStorage.cloudStorage.security.filter.AuthorizationFilter;
import com.cloudStorage.cloudStorage.util.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    private AuthorizationFilter authorizationFilter;

    public SecurityConfig(AuthorizationFilter authorizationFilter){
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtUtils jwtUtils,AuthenticationManager authenticationManager) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtUtils);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->{
                auth.requestMatchers( "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/v2/api-docs/**",
                                    "/swagger-resources/**").permitAll();
                auth.requestMatchers("/login","/api/user/create").permitAll();
                auth.anyRequest().authenticated();
            })
            .addFilter(authenticationFilter)
            .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();
    }
}