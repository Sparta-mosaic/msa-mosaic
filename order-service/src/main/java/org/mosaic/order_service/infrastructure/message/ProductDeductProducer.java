package org.mosaic.order_service.infrastructure.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mosaic.order_service.application.dtos.ProductDeductDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ProductDeductProducer in OrderServer")
public class ProductDeductProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final AuditorAware<String> auditorAware;
  private final ObjectMapper objectMapper;

  public void send(String topic, String orderUuid, List<ProductDeductDto> dto) {
    try {
      String userId = auditorAware.getCurrentAuditor().orElse("unknown");

      String jsonPayload = objectMapper.writeValueAsString(dto);

      ProducerRecord<String, String> producerRecord =
          new ProducerRecord<>(topic, orderUuid, jsonPayload);
      producerRecord.headers().add("UUID", orderUuid.getBytes(StandardCharsets.UTF_8));
      producerRecord.headers().add("USER_ID", userId.getBytes(StandardCharsets.UTF_8));

      kafkaTemplate
          .send(producerRecord)
          .whenComplete(
              (result, ex) -> {
                if (ex == null) {
                  log.info(
                      "Sent ProductDeductDto of {} to ProductServer with auditor {}",
                      orderUuid,
                      userId);
                } else {
                  log.error("Error sending ProductDeductDto of {} to ProductServer", orderUuid, ex);
                }
              });
    } catch (JsonProcessingException e) {
      log.error("Error serializing ProductDeductDto for order {}", orderUuid, e);
    }
  }
}
