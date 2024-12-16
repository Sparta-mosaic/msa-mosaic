package org.mosaic.order_service.libs.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.mosaic.order_service.infrastructure.message.ProductDeductProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.kafka.core.KafkaTemplate;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@RequiredArgsConstructor
@Configuration
public class KafkaConfig {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final AuditorAware<String> auditorAware;
  private final ObjectMapper objectMapper;

  @Bean
  public ProductDeductProducer productDeductProducer() {
    return new ProductDeductProducer(kafkaTemplate, auditorAware, objectMapper);
  }
}
