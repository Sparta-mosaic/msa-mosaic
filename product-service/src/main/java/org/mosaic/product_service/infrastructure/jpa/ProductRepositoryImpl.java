package org.mosaic.product_service.infrastructure.jpa;

import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryImpl extends JpaRepository<Product, Long>,
	ProductRepository {

}
