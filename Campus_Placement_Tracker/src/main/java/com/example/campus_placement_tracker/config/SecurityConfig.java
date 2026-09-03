package com.example.campus_placement_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .cors(cors -> cors.configurationSource(
                        corsConfigurationSource()
                ))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> {

                    // PUBLIC AUTH ENDPOINTS
                    auth.requestMatchers(
                            "/api/auth/register/student",
                            "/api/auth/register/company",
                            "/api/auth/login"
                    ).permitAll();

                    auth.requestMatchers(
                            HttpMethod.GET,
                            "/api/applications/drive/*/count"
                    ).permitAll();

                    // PUBLIC GET DRIVES
                    auth.requestMatchers(
                            HttpMethod.GET,
                            "/api/drives"
                    ).permitAll();


                    // DRIVE OPERATIONS
                    auth.requestMatchers(
                            "/api/drives/**"
                    ).hasAnyRole(
                            "STUDENT",
                            "COMPANY",
                            "ADMIN"
                    );


                    // APPLICATIONS
                    auth.requestMatchers(
                            "/api/applications/**"
                    ).hasAnyRole(
                            "STUDENT",
                            "ADMIN"
                    );


                    // INTERVIEWS
                    auth.requestMatchers(
                            "/api/interviews/**"
                    ).hasAnyRole(
                            "COMPANY",
                            "ADMIN"
                    );


                    // STUDENTS
                    auth.requestMatchers(
                            "/api/students/**"
                    ).hasAnyRole(
                            "STUDENT",
                            "ADMIN"
                    );


                    // COMPANIES
                    auth.requestMatchers(
                            "/api/companies/**"
                    ).hasAnyRole(
                            "COMPANY",
                            "ADMIN"
                    );


                    // EVERYTHING ELSE
                    auth.anyRequest().authenticated();
                })

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}