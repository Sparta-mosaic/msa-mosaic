package org.mosaic.order_service.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.order_service.domain.enums.OrderState;
import org.mosaic.order_service.libs.common.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "P_ORDERS")
public class Order extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Long orderId;

  @Column(
      name = "ORDER_UUID",
      updatable = false,
      nullable = false,
      columnDefinition = "VARCHAR(36)")
  private String orderUuid;

  @Column(name = "SUPPLIER_COMPANY_ID", nullable = false)
  private String supplierCompanyId;

  @Column(name = "RECEIVER_COMPANY_ID", nullable = false)
  private String receiverCompanyId;

  @Enumerated(EnumType.STRING)
  @Column(name = "ORDER_STATE", nullable = false)
  private OrderState orderState = OrderState.CREATED;

  @Column(name = "ORDER_DATE", nullable = false)
  private LocalDateTime orderDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderDetail> orderDetails = new ArrayList<>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderStateHistory> orderStateHistory = new ArrayList<>();

  @Column(name = "TOTAL_AMOUNT", nullable = false)
  private Long totalAmount = 0L;

  @Column(name = "TOTAL_QUANTITY", nullable = false)
  private Long totalQuantity = 0L;

  @Builder
  private Order(
      String supplierCompanyId,
      String receiverCompanyId,
      OrderState orderState,
      LocalDateTime orderDate,
      String orderUuid) {
    this.supplierCompanyId = supplierCompanyId;
    this.receiverCompanyId = receiverCompanyId;
    this.orderState = orderState;
    this.orderDate = orderDate;
    this.orderUuid = orderUuid;
  }

  public void addOrderDetails(List<OrderDetail> details) {
    for (OrderDetail detail : details) {
      addOrderDetail(detail);
    }
  }

  private void addOrderDetail(OrderDetail orderDetail) {
    this.orderDetails.add(orderDetail);
    orderDetail.changeOrder(this);
    recalculateTotals();
  }

  public void addOrderStateHistory(OrderState state) {
    OrderStateHistory history = OrderStateHistory.builder().orderState(state).build();
    this.orderState = state;
    history.changeOrder(this);
    this.orderStateHistory.add(history);
  }

  private void recalculateTotals() {
    this.totalQuantity = orderDetails.stream().mapToLong(OrderDetail::getQuantity).sum();
    this.totalAmount =
        orderDetails.stream()
            .mapToLong(detail -> detail.getQuantity() * detail.getUnitPrice())
            .sum();
  }
}
