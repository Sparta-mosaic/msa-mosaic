package org.mosaic.auth.libs.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.libs.security.entity.AuthenticatedUserDto;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.security.service.JwtService;
import org.mosaic.auth.libs.security.utils.JwtUtil;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            log.debug("JWTFilter - Authentication already exists in SecurityContext. Skipping.");
            filterChain.doFilter(request, response);
            return;
        }

        String requestURI = request.getRequestURI();
        String authorization = request.getHeader("Authorization");

        log.debug("JWTFilter - Processing request: {}, Authorization header: {}", request.getRequestURL(), authorization);

        if (requestURI.equals("/api/v1/auth/login") ||
            requestURI.equals("/api/v1/auth/signIn") ||
            requestURI.equals("/api/v1/admin/auth/signIn")) {
            log.debug("JWTFilter - Skipping authentication for public endpoints: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }


        String token = authorization.substring(7);

        try {

            String userUuid = jwtUtil.getUserUuid(token);
            UserRole role = jwtUtil.getRole(token);

            AuthenticatedUserDto authenticatedUserDto = AuthenticatedUserDto.createAuthenticatedUserDto(userUuid, role, true);
            CustomUserDetails customOAuth2User = new CustomUserDetails(authenticatedUserDto);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (BadCredentialsException e) {
            log.error("JWTFilter - Authentication failed: {}", e.getMessage());
            handleUnauthorizedRedirect(response);
            return;
        }

        filterChain.doFilter(request, response);
    }



    private void handleUnauthorizedRedirect(HttpServletResponse response) throws IOException {
        log.info("JWTFilterV1 - Unauthorized access, redirecting to login.");
        response.sendRedirect("/login?unauthorizedRedirect=true");
    }
}
