package org.mosaic.auth.libs.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.libs.security.utils.JwtUtil;
import org.mosaic.auth.user.application.service.UserQueryService;
import org.mosaic.auth.user.domain.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  private final UserQueryService userService;
  private final JwtUtil jwtUtil;
  private final ObjectMapper objectMapper;

  private static final String CONTENT_TYPE = "application/json";

  public LoginFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper,
      UserQueryService userService, JwtUtil jwtUtil) {
    super(
        new AntPathRequestMatcher("/api/v1/auth/login", "POST")
        , authenticationManager);
    this.userService = userService;
    this.jwtUtil = jwtUtil;
    this.objectMapper = objectMapper;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    try {
      if (SecurityContextHolder.getContext().getAuthentication() != null) {
        log.debug("LoginFilter - SecurityContext already contains authentication. Skipping.");
        return SecurityContextHolder.getContext().getAuthentication();
      }

      if (request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)) {
        throw new AuthenticationServiceException("Invalid content type");
      }

      String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
      Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);

      String username = usernamePasswordMap.get("username");
      String password = usernamePasswordMap.get("password");

      if (username == null || password == null) {
        throw new AuthenticationServiceException("Missing email or password");
      }

      User authenticatedUser = userService.getAuthenticateUser(username, password);
      return new UsernamePasswordAuthenticationToken(authenticatedUser.getUsername(), password);
    } catch (Exception e) {
      log.error("LoginFilter - Error during authentication: {}", e.getMessage());
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication failed");
    }

    return null;
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authentication
  ) throws IOException, ServletException {

    String username = authentication.getName();
    User user = userService.getUserByUsername(username);

    String newAccess = jwtUtil.createAccessToken(
        user.getUserUUID(),
        user.getRole().toString());

    addResponseData(response, newAccess);
  }

  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed
  ) throws IOException, ServletException {

    log.info("로그인에 실패했습니다: {}", failed.getMessage());

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    Map<String, Object> responseData = new HashMap<>();
    responseData.put("timestamp", System.currentTimeMillis());
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    responseData.put("error", "Unauthorized");
    responseData.put("message", "로그인에 실패했습니다.");

    response.getWriter().write(objectMapper.writeValueAsString(responseData));
  }

  // 쿠키 저장
  private void addResponseData(HttpServletResponse response, String accessToken)
      throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    Map<String, String> responseData = new HashMap<>();
    responseData.put("accessToken", accessToken);

    response.getWriter().write(objectMapper.writeValueAsString(responseData));
  }
}
