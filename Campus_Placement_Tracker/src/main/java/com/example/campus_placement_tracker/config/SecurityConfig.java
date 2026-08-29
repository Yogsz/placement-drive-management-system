package com.example.campus_placement_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers(
                                "/api/auth/register/student",
                                "/api/auth/register/company",
                                "/api/auth/login"
                        ).permitAll()


                        // DRIVES
                        .requestMatchers("/api/drives/**")
                        .hasAnyRole(
                                "STUDENT",
                                "COMPANY",
                                "ADMIN"
                        )


                        // APPLICATIONS
                        .requestMatchers("/api/applications/**")
                        .hasAnyRole(
                                "STUDENT",
                                "ADMIN"
                        )


                        // INTERVIEWS
                        .requestMatchers("/api/interviews/**")
                        .hasAnyRole(
                                "COMPANY",
                                "ADMIN"
                        )


                        // STUDENTS
                        .requestMatchers("/api/students/**")
                        .hasAnyRole(
                                "STUDENT",
                                "ADMIN"
                        )


                        // COMPANIES
                        .requestMatchers("/api/companies/**")
                        .hasAnyRole(
                                "COMPANY",
                                "ADMIN"
                        )


                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}