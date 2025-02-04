// package com.bookmyshow_experience.Book_my_show_experience.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// public class SecurityConfig {

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Autowired
// private JwtFilter jwtFilter;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// System.out.println("Inside filter chain");
// return http.csrf(csrf -> csrf.disable())
// .authorizeHttpRequests(
// auth -> auth.requestMatchers("/api/v1/exp/user/login",
// "/api/v1/exp/user/register").permitAll()
// .anyRequest().authenticated())
// .sessionManagement(sess ->
// sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
// .build();
// }
// }
