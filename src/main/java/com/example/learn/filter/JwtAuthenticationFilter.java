package com.example.learn.filter;

import com.example.learn.service.jwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private jwtService Jwts;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        System.out.println(token);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Claims claim = Jwts.getAllClaim(token);
            String username = claim.getSubject();
            String role = claim.get("role", String.class);
            System.out.println("Username: " + username);
            System.out.println("Role: " + role);
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            if (Jwts.isTokenValid(token)) {
                UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(username, null,
                        authorities);

                tokens.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(tokens);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired or invalid");
                return;
            }
        }
        filterChain.doFilter(request, response);

    }
}
