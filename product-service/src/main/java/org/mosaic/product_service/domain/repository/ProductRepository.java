package org.mosaic.product_service.domain.repository;

import org.mosaic.product_service.domain.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
	Product save(Product product);
}
