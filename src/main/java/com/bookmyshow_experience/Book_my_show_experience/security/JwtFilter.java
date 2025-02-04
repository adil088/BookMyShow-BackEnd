// package com.bookmyshow_experience.Book_my_show_experience.security;

// import java.io.IOException;
// import java.util.Collections;

// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

// @Autowired
// private JwtUtil jwtUtil;

// protected void doFilterInternal(HttpServletRequest request,
// HttpServletResponse response, FilterChain filterChain)
// throws IOException, ServletException {
// String authHeader = request.getHeader("Authorization");

// if (authHeader != null && authHeader.startsWith("Bearer ")) {
// String token = authHeader.substring(7).trim();

// if (!jwtUtil.validateToken(token)) {
// System.out.println("Invalid JWT token");
// filterChain.doFilter(request, response);
// return;
// }
// String credentials = jwtUtil.extractCredentials(token);
// System.out.println("Authenticated credentials: " + credentials);
// UsernamePasswordAuthenticationToken authenticationToken = new
// UsernamePasswordAuthenticationToken(
// credentials, null, Collections.emptyList());
// SecurityContextHolder.getContext().setAuthentication(authenticationToken);
// }

// filterChain.doFilter(request, response);
// }

// }
