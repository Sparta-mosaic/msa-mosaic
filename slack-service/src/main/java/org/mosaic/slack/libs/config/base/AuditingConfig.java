package org.mosaic.slack.libs.config.base;

import static org.mosaic.slack.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mosaic.slack.libs.exception.CustomException;
import org.mosaic.slack.libs.exception.ExceptionStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
