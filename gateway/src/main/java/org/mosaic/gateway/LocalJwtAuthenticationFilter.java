package org.mosaic.gateway;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LocalJwtAuthenticationFilter implements GlobalFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        if(path.equals("/api/v1/auth/login") || path.equals("/api/v1/auth/signUp") || path.equals("/api/v1/admin/auth/signUp")) {
            return chain.filter(exchange);
        }

        String authorizationHeader = exchange.getRequest().getHeaders()
            .getFirst(HttpHeaders.AUTHORIZATION);

        if (isNotValidHeader(authorizationHeader)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        String token = authorizationHeader.replace(AUTHENTICATION_SCHEME, "");
        if (isNotValidToken(key, token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        ServerHttpRequest httpRequest = generateRequest(exchange, key, token);

        return chain.filter(exchange.mutate().request(httpRequest).build());
    }

    private ServerHttpRequest generateRequest(
        ServerWebExchange exchange, SecretKey key, String token) {

        Claims claims = getClaims(key, token);

        String userUuid = claims.get("userUuid", String.class);
        String userRole = claims.get("role", String.class);

      return exchange.getRequest().mutate()
          .header("X-User-Id", userUuid)
          .header("X-User-Role", userRole)
          .build();
    }

    private Claims getClaims(SecretKey key, String token) {
      return Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token)
          .getPayload();
    }

    private boolean isNotValidHeader(String authorizationHeader) {
        return !StringUtils.hasText(authorizationHeader)
            || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME);
    }

    private boolean isNotValidToken(SecretKey key, String jwt) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
