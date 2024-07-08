package com.aalmendaris.CarRegistry.filter;

import com.aalmendaris.CarRegistry.service.impl.JwtService;
import com.aalmendaris.CarRegistry.service.impl.UserServiceimpl;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserServiceimpl userServiceimpl;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        /**
         * Aqui verificamos la existencia del Header,
         * si el encabezado Autorizacion esat vacio, se pasa la solicitud al siguiente filtro en la cadena sin hacer nada más.
         */
        if (StringUtils.isEmpty(authHeader)){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Bearer xxxx
        log.info("JWT -> {}", jwt.toString());

        userEmail = jwtService.extractUserName(jwt);
        if (!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userServiceimpl.userDetailsService().loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt, userDetails)) {
                log.info("User -> {}", userDetails);
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
