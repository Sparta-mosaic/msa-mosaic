package org.mosaic.order_service.libs.common.config;

import static org.mosaic.order_service.libs.common.constant.HttpHeaderConstants.*;

import java.util.Optional;

import org.mosaic.order_service.libs.exception.AuthException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaAuditConfig {
  private final HttpServletRequest request;

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      String header =
          Optional.ofNullable(request.getHeader(HEADER_USER_ID))
              .orElseThrow(() -> new AuthException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."));
      return Optional.of(header);
    };
  }
}
