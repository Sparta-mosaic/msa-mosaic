package org.mosaic.order_service.infrastructure.client;

import static org.mosaic.order_service.libs.common.constant.HttpHeaderConstants.HEADER_USER_ID;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.libs.exception.AuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class FeignClientConfig {

  private final AuditorAware<String> auditorAware;

  @Value("${server.port}")
  private String serverPort;

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.HEADERS;
  }

  @Bean
  public RequestInterceptor requestInterceptor() {

    String userId =
        auditorAware
            .getCurrentAuditor()
            .orElseThrow(() -> new AuthException(HttpStatus.BAD_REQUEST, "접근 할 수 없습니다."));

    return requestTemplate -> {
      requestTemplate.header(HEADER_USER_ID, userId);
      requestTemplate.header("SERVER-PORT", serverPort);
    };
  }
}
