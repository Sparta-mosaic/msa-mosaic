package org.mosaic.order_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.mosaic.order_service.libs.common.entity.BaseEntity;

@Getter
@Entity
@Table(name = "P_ORDER_DETAILS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_DETAIL_ID")
  private Long OrderDetailId;

  @Column(
      name = "ORDER_DETAIL_UUID",
      updatable = false,
      nullable = false,
      columnDefinition = "VARCHAR(36)")
  private String orderDetailUuid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ORDER_ID", nullable = false)
  private Order order;

  @Column(name = "PRODUCT_ID", nullable = false)
  private String productId;

  @Column(name = "PRODUCT_NAME", nullable = false)
  private String productName;

  @Column(name = "QUANTITY", nullable = false)
  private Long quantity;

  @Column(name = "UNIT_PRICE", nullable = false)
  private Long unitPrice;

  @Builder
  public OrderDetail(
      String orderDetailUuid,
      Order order,
      String productId,
      String productName,
      Long quantity,
      Long unitPrice) {
    this.orderDetailUuid = orderDetailUuid;
    this.order = order;
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  void changeOrder(Order order) {
    this.order = order;
  }
}
