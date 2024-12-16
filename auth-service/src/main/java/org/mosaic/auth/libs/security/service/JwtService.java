package org.mosaic.auth.libs.security.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.libs.security.utils.JwtUtil;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

  private final JwtUtil jwtUtil;

  private static final Long ACCESS_TOKEN_EXPIRATION = 60L * 30; // 30 분

  public String reissueAccessToken(HttpServletRequest request) {
    String accessToken = getAccessTokenFromRequest(request);

    // 액세스 토큰 유효성 검사
    if (!jwtUtil.validateToken(accessToken)) {
      throw new IllegalArgumentException("Invalid access token");
    }

    String userUuid = jwtUtil.getUserUuid(accessToken);
    UserRole role = jwtUtil.getRole(accessToken);

    // 새 액세스 토큰 생성
    return jwtUtil.createAccessToken(String.valueOf(userUuid), role.toString());
  }

  public String getAccessTokenFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("No access token found in the request");
    }
    return authorizationHeader.substring(7);
  }
}
