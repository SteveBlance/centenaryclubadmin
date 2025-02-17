package com.codaconsultancy.centenaryclubadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/static/dist/css/sb-admin-2.css", "/static/vendor/font-awesome/css/font-awesome.min.css","/resources/**", "/static/css/**", "/static/dist/css/**", "/static/images/**",
                                "/static/js/**", "/static/less/**", "/static/vendor/morrisjs/**",
                                "/static/vendor/bootstrap/css/**", "/static/vendor/bootstrap/fonts/**", "/static/vendor/bootstrap/js/**",
                                "/static/vendor/font-awesome/css/**", "/static/vendor/font-awesome/fonts/**", "/static/vendor/font-awesome/less/**", "/static/vendor/font-awesome/scss/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
