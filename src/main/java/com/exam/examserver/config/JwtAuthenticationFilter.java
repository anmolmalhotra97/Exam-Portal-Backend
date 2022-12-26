package com.exam.examserver.config;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("UserDetailsSpringSecurityServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        logger.info("Authorization:  " + requestTokenHeader);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            try {
                jwtToken = requestTokenHeader.substring(7);
                username = this.jwtUtils.extractUsername(jwtToken);
            } catch (ExpiredJwtException expiredJwtException) {
                expiredJwtException.printStackTrace();
                log.error("JWT Token has Expired");
            } catch (Exception exception) {
                log.error("JWT Token Exception with message: " + exception.getMessage());
            }
        } else {
            log.error("Invalid JWT Token: Token is either Null or doesn't start with 'Bearer'");
        }

        //Validate the Token.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(jwtToken, userDetails)) {

                //Token is Valid, hence set authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            log.error("JWT Token is NOT Valid");
        }
        filterChain.doFilter(request, response);
    }

}

