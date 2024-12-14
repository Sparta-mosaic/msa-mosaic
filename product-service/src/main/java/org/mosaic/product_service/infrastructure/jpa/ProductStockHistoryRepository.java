package org.mosaic.product_service.infrastructure.jpa;

import org.mosaic.product_service.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockHistoryRepository extends
	JpaRepository<Product, Long> {
	void deleteAllByProductUuid(String productUuid);
}
