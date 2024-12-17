package org.mosaic.delivery_service.infrastructure.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.delivery_service.apllication.dtos.CompanyHubUuidDto;
import org.mosaic.delivery_service.apllication.dtos.DeliveryMessage;
import org.mosaic.delivery_service.apllication.service.DeliveryMessageService;
import org.mosaic.delivery_service.libs.common.config.JpaAuditConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryMessageConsumer {

  private final ObjectMapper objectMapper;
  private final JpaAuditConfig jpaAuditConfig;
  private final DeliveryMessageService deliveryMessageService;

  @KafkaListener(topics = "DELIVERY_SERVICE_TOPIC", groupId = "delivery-service")
  @Transactional
  public void handleDeliveryMessage(
      @Header(KafkaHeaders.RECEIVED_KEY) String orderUuid, @Payload String payload) {
    try {
      log.info("Received delivery message for order UUID: {}", orderUuid);
      String decodedPayload = objectMapper.readValue(payload, String.class);
      DeliveryMessage message = objectMapper.readValue(decodedPayload, DeliveryMessage.class);

      String orderId = message.getOrderId();
      String userId = message.getUserId();
      jpaAuditConfig.getUserId(userId);
      List<CompanyHubUuidDto> companyHubUuidDtos = message.getCompanyHubUuidDtos();

      deliveryMessageService.processDelivery(orderId, userId, companyHubUuidDtos);

      log.info("Successfully processed delivery message for order UUID: {}", orderUuid);
    } catch (Exception e) {
      log.error(
          "Error processing delivery message for order UUID: {}, Error: {}",
          orderUuid,
          e.getMessage());
    }
  }
}
