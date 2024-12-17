package org.mosaic.order_service.infrastructure.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.order_service.application.dtos.CompanyHubUuidDto;
import org.mosaic.order_service.application.dtos.DeliveryMessage;
import org.mosaic.order_service.application.service.OrderMessageService;
import org.mosaic.order_service.libs.common.config.JpaAuditConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDeductResultConsumer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final OrderMessageService orderMessageService;
  private final JpaAuditConfig jpaAuditConfig;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "SUCCESS_PRODUCT_QUANTITY", groupId = "order-service")
  public void handleSuccessDeduction(
      @Header(KafkaHeaders.RECEIVED_KEY) String orderUuid,
      @Header("USER_ID") String userId,
      @Payload String payload) {
    try {
      log.info("Product deduction successful for order UUID: {}, User ID: {}", orderUuid, userId);
      jpaAuditing(userId);

      List<CompanyHubUuidDto> companyHubUuidDtos = deserializePayload(payload);

      orderMessageService.changeOrderStateToCreated(orderUuid);

      String deliveryMessage = createDeliveryMessage(orderUuid, userId, companyHubUuidDtos);
      kafkaTemplate.send("DELIVERY_SERVICE_TOPIC", orderUuid, deliveryMessage);

      log.info("Sent delivery message for order UUID: {}", orderUuid);
    } catch (Exception e) {
      log.error(
          "Error processing success message for order UUID: {}, Error: {}",
          orderUuid,
          e.getMessage());
    }
  }

  @KafkaListener(topics = "ERROR_PRODUCT_QUANTITY", groupId = "order-service")
  public void handleErrorDeduction(
      @Payload String orderUuid, @Header("USER_ID") String userId, @Payload String errorMessage) {
    log.error(
        "Product deduction failed for order UUID: {}, User ID: {}. Error: {}",
        orderUuid,
        userId,
        errorMessage);
    jpaAuditing(userId);
    orderMessageService.changeOrderStateToCancel(orderUuid);
  }

  private String createDeliveryMessage(
      String orderId, String userId, List<CompanyHubUuidDto> companyHubUuidDtos)
      throws JsonProcessingException {
    DeliveryMessage message = new DeliveryMessage(orderId, userId, companyHubUuidDtos);
    return objectMapper.writeValueAsString(message);
  }

  private List<CompanyHubUuidDto> deserializePayload(String payload)
      throws JsonProcessingException {
    return objectMapper.readValue(payload, new TypeReference<List<CompanyHubUuidDto>>() {});
  }

  private void jpaAuditing(String userId) {
    jpaAuditConfig.getUserId(userId);
  }
}
