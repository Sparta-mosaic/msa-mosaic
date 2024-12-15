package org.mosaic.order_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.order_service.domain.enums.OrderState;
import org.mosaic.order_service.libs.common.entity.BaseEntity;

@Getter
@Entity
@Table(name = "P_ORDER_STATE_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderStateHistory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_STATE_HISTORY_ID")
  private Long orderStateHistoryId;

  @Enumerated(EnumType.STRING)
  @Column(name = "ORDER_STATE", nullable = false)
  private OrderState orderState;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ORDER_ID", nullable = false)
  private Order order;

  @Builder
  private OrderStateHistory(OrderState orderState) {
    this.orderState = orderState;
  }

  public void changeOrder(Order order) {
    this.order = order;
  }
}
