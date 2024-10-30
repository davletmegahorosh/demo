package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET, "/book_list/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/book_list/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/book_list/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/book_list/**").permitAll()

                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/cart/add").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cart/view").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cart/purchase").permitAll()

                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
