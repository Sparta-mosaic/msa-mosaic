package org.mosaic.product_service.infrastructure.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.mosaic.product_service.application.dtos.CompanyHubUuidDto;
import org.mosaic.product_service.application.dtos.ProductDeductDto;
import org.mosaic.product_service.application.service.ProductMessageService;
import org.mosaic.product_service.libs.common.config.JpaAuditConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j(topic = "ProductDeductConsumer in ProductServer")
public class ProductDeductConsumer {

  private final ProductMessageService productMessageService;
  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final JpaAuditConfig jpaAuditConfig;

  @KafkaListener(topics = "PRODUCT_DEDUCT_QUANTITY", groupId = "deduct-product")
  @SendTo("PRODUCT_DEDUCT_REPLY")
  public void handleProductQuantityDeduction(
      @Header(KafkaHeaders.RECEIVED_KEY) String key,
      @Header("UUID") String orderUuid,
      @Header("USER_ID") String userId,
      @Payload String payload) {

    try {
      log.info("Received message with UUID: {} and userId: {}", orderUuid, userId);
      jpaAuditConfig.getUserId(userId);

      List<ProductDeductDto> productDeductDto = deserializePayload(payload);
      List<CompanyHubUuidDto> companyHubUuidDto =
          productMessageService.deductProductQuantity(productDeductDto);

      log.info("Successfully processed deduction for order: {}", key);
      sendSuccessMessage(orderUuid, userId, companyHubUuidDto);
    } catch (Exception e) {
      sendErrorMessage(orderUuid, userId, e.getMessage());
      log.error("Error occurred while deduct product : {}", e.getMessage());
    }
  }

  private void sendSuccessMessage(
      String orderUuid, String userId, List<CompanyHubUuidDto> companyHubUuidDto) {
    try {
      String companyHubUuidDtoJson = objectMapper.writeValueAsString(companyHubUuidDto);
      ProducerRecord<String, String> producerRecord =
          new ProducerRecord<>("SUCCESS_PRODUCT_QUANTITY", orderUuid, companyHubUuidDtoJson);
      producerRecord.headers().add("USER_ID", userId.getBytes(StandardCharsets.UTF_8));
      kafkaTemplate.send(producerRecord);
    } catch (JsonProcessingException e) {
      log.error("Error serializing CompanyHubUuidDto: {}", e.getMessage());
      sendErrorMessage(orderUuid, userId, "Error processing success message");
    }
  }

  private void sendErrorMessage(String orderUuid, String userId, String errorMessage) {
    String fullErrorMessage = String.format("Order UUID: %s, Error: %s", orderUuid, errorMessage);
    ProducerRecord<String, String> producerRecord =
        new ProducerRecord<>("ERROR_PRODUCT_QUANTITY", orderUuid, fullErrorMessage);
    producerRecord.headers().add("USER_ID", userId.getBytes(StandardCharsets.UTF_8));
    kafkaTemplate.send(producerRecord);
  }

  private List<ProductDeductDto> deserializePayload(String payload) throws JsonProcessingException {
    return objectMapper.readValue(payload, new TypeReference<List<ProductDeductDto>>() {});
  }
}
