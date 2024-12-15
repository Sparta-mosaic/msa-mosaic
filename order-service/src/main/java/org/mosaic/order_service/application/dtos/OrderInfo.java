package org.mosaic.order_service.application.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.order_service.domain.enums.OrderState;

@Getter
@AllArgsConstructor
public class OrderInfo {
  private String orderUuid;
  private String supplierCompanyId;
  private String receiverCompanyId;
  private OrderState orderState;
  private LocalDateTime orderDate;
  private Long totalAmount;
  private Long totalQuantity;
}
