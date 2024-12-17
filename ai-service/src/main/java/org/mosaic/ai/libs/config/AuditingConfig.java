package org.mosaic.ai.libs.config;


import static org.mosaic.ai.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.ai.libs.exception.CustomException;
import org.mosaic.ai.libs.exception.ExceptionStatus;
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
              () -> new CustomException(ExceptionStatus.BAD_REQUEST));
      return Optional.of(header);
    };
  }
}
