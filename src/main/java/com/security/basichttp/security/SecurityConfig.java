package com.security.basichttp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // Defining the user(in-memory)

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        var user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        var admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        var superAdmin = User.withUsername("prashik").
                password(encoder.encode("Pass@123")).
                roles("SUPERADMIN").build()
                ;

        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user, admin, superAdmin);
    }

    // Define password encoder

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Define security filter chain

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin").hasAnyRole("ADMIN","SUPERADMIN")// access to admin and super admin
                        .requestMatchers("/super").hasRole("SUPERADMIN") // access to only superadmin
                        .requestMatchers("/hello").authenticated() // access to any authenticated user
                        .requestMatchers("/welcome").authenticated() // access to any authenticated user
                        .requestMatchers("/public").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(); // enable HTTP Basic Auth

        return http.build();
    }



}
