package org.mosaic.order_service.infrastructure.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.order_service.application.service.OrderMessageService;
import org.mosaic.order_service.libs.common.config.JpaAuditConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDeductResultConsumer {

  private final OrderMessageService orderMessageService;
  private final JpaAuditConfig jpaAuditConfig;

  @KafkaListener(topics = "SUCCESS_PRODUCT_QUANTITY", groupId = "order-service")
  public void handleSuccessDeduction(@Payload String orderUuid, @Header("USER_ID") String userId) {
    log.info("Product deduction successful for order UUID: {}, User ID: {}", orderUuid, userId);
    jpaAuditing(userId);
    orderMessageService.changeOrderStateToCreated(orderUuid);
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

  private void jpaAuditing(String userId) {
    jpaAuditConfig.getUserId(userId);
  }
}
