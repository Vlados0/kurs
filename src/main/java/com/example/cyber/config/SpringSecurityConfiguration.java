package com.example.cyber.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import com.example.cyber.model.UserAuthority;


@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers(HttpMethod.POST, "/orders").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/orders/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.POST, "/reviews").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/reviews/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAuthority(UserAuthority.FULL.getAuthority())
                                .requestMatchers(HttpMethod.POST, "/products").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/products/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority(UserAuthority.FULL.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority(UserAuthority.FULL.getAuthority())
                                .requestMatchers(HttpMethod.POST, "/users").hasAuthority(UserAuthority.PLACE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority(UserAuthority.MANAGE_ORDERS.getAuthority())
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority(UserAuthority.FULL.getAuthority())
                                .anyRequest().hasAuthority(UserAuthority.FULL.getAuthority()))
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

