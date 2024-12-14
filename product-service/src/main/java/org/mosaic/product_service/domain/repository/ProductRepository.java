package org.mosaic.product_service.domain.repository;

import java.util.Optional;

import org.mosaic.product_service.domain.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
	Product save(Product product);
	Optional<Product> findByProductUuid(String uuid);
	void delete(Product product);
}
