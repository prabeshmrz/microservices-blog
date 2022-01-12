package com.javaproject.microservices.blog.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationRequestFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;

    String token = null;

    if (
      authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
    ) {
      token = authorizationHeader.substring(7);
      username = jwtUtil.extractUsername(token);
    }

    if (
      username != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      var userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails.getUsername(),
          userDetails.getPassword()
        );
        usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder
          .getContext()
          .setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
