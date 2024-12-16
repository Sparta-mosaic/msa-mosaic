package org.mosaic.gateway;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LocalJwtAuthenticationFilter implements GlobalFilter {

    @Value("${service.jwt.secret}")
    private String secretKey;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        if(path.equals("/api/v1/auth/login") || path.equals("/api/v1/auth/signUp") || path.equals("/api/v1/admin/auth/signUp")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);

        if(token == null || !validateToken(exchange, token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 헤더에 필요한 authorization 내용이 들어오는 지 확인
        if(authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }


    private boolean validateToken(ServerWebExchange exchange, String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);
            log.info("#####payload :: " + claimsJws.getPayload().toString());

            Claims claims = claimsJws.getPayload();
            exchange.getRequest().mutate()
                .header("X-User-Id", claims.get("userUuid").toString())
                .header("X-Role", claims.get("role").toString())
                .header("Authorization", token)
                .build();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
