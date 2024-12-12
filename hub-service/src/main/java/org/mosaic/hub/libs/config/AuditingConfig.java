package org.mosaic.hub.libs.config;

import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.hub.libs.exception.CustomException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class AuditingConfig {

  private final HttpServletRequest request;

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      String header = Optional.ofNullable(request.getHeader(HEADER_USER_ID))
          .orElseThrow(
              () -> new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."));
      return Optional.of(header);
    };
  }
}
