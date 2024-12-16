package org.mosaic.product_service.libs.common.config;

import static org.mosaic.product_service.libs.common.constant.HttpHeaderConstants.HEADER_USER_ID;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.product_service.libs.exception.AuthException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaAuditConfig {

  private final HttpServletRequest request;
  private String userId;

  @Bean
  public AuditorAware<String> auditorProvider() {
    return this::getCurrentAuditor;
  }

  public void getUserId(String userId) {
    this.userId = userId;
  }

  // Private 메서드
  private Optional<String> getCurrentAuditor() {
    return Optional.ofNullable(userId).or(this::getHeaderUserId).or(this::throwAuthException);
  }

  private Optional<String> getHeaderUserId() {
    return Optional.ofNullable(request.getHeader(HEADER_USER_ID));
  }

  private Optional<String> throwAuthException() {
    throw new AuthException(HttpStatus.UNAUTHORIZED, "잘못된 요청입니다.");
  }
}
