package org.mosaic.product_service.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.mosaic.product_service.domain.entity.enums.StockType;
import org.mosaic.product_service.libs.common.entity.BaseEntity;
import org.mosaic.product_service.libs.exception.InsufficientStockException;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "P_PRODUCTS")
@SQLRestriction("IS_DELETE = FALSE AND IS_PUBLIC = TRUE")
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(
      name = "PRODUCT_UUID",
      updatable = false,
      nullable = false,
      columnDefinition = "VARCHAR(36)")
  private String productUuid;

  @Column(name = "COMPANY_ID", nullable = false)
  private String companyId;

  @Column(name = "PRODUCT_HUB_ID", nullable = false)
  private String productHubId;

  @Column(name = "PRODUCT_NAME", nullable = false)
  private String productName;

  @Column(name = "PRODUCT_PRICE", nullable = false)
  private Long productPrice;

  @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
  private String productDescription;

  @Column(name = "STOCK_QUANTITY", nullable = false)
  private Long stockQuantity;

  @Version
  @Column(name = "VERSION")
  private Long version;

  @OneToMany(
      mappedBy = "product",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<ProductStockHistory> stockHistories = new ArrayList<>();

  public void addStockHistory(Long quantity, StockType type) {
    this.stockHistories.add(
        ProductStockHistory.builder().product(this).quantity(quantity).stockType(type).build());
  }

  @PrePersist
  private void init() {
    this.productUuid = UUID.randomUUID().toString();
    this.isDelete = false;
    this.isPublic = true;
  }

  @Builder
  private Product(
      String companyId,
      String productHubId,
      String productName,
      Long productPrice,
      String productDescription,
      Long stockHistories) {
    this.companyId = companyId;
    this.productHubId = productHubId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productDescription = productDescription;
    this.stockQuantity = stockHistories;
  }

  public void update(
      String companyId,
      String productHubId,
      String productName,
      Long productPrice,
      String productDescription,
      Long stockHistories) {
    this.companyId = companyId;
    this.productHubId = productHubId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productDescription = productDescription;
    this.stockQuantity = stockHistories;
  }

  public void deductQuantity(Long quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("차감할 수량은 0보다 커야 합니다.");
    }
    if (stockQuantity == 0) {
      throw new InsufficientStockException("재고가 없습니다.");
    }
    if (stockQuantity < quantity) {
      throw new InsufficientStockException(
          "요청한 수량(" + quantity + ")이 현재 재고(" + stockQuantity + ")보다 많습니다.");
    }
    this.stockQuantity -= quantity;
    addStockHistory(quantity, StockType.OUTBOUND);
  }
}
