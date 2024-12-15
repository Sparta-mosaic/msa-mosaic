package org.mosaic.product_service.domain.entity;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.mosaic.product_service.domain.entity.enums.StockType;
import org.mosaic.product_service.libs.common.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "P_PRODUCTS_STOCK_HISTORY")
@SQLRestriction("IS_DELETE = FALSE AND IS_PUBLIC = TRUE")
public class ProductStockHistory extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_STOCK_HISTORY_ID")
	private Long productStockHistoryID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@Column(name = "QUANTITY")
	private Long quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "STOCK_TYPE")
	private StockType stockType;

	@Builder
	private ProductStockHistory(Product product, Long quantity, StockType stockType) {
		this.product = product;
		this.quantity = quantity;
		this.stockType = stockType;
	}

	public void update(Long quantity) {
		this.quantity = quantity;
	}
}


