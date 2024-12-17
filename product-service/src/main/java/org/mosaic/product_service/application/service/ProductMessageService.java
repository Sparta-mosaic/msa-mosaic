package org.mosaic.product_service.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.product_service.application.dtos.CompanyHubUuidDto;
import org.mosaic.product_service.application.dtos.ProductDeductDto;
import org.mosaic.product_service.domain.entity.Product;
import org.mosaic.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductMessageService {

  private final ProductRepository productRepository;
  private final ProductQueryService productQueryService;

  @Transactional
  public List<CompanyHubUuidDto> deductProductQuantity(List<ProductDeductDto> dtos) {
    List<CompanyHubUuidDto> responses = new ArrayList<>();
    try {
      for (ProductDeductDto dto : dtos) {
        Product product = productQueryService.findProductUuid(dto.getProductUuid());
        product.deductQuantity(dto.getQuantity());
        Product savedProduct = productRepository.save(product);
        log.info("Deducted product {} quantity by createOrder", product.getProductName());

        responses.add(
            CompanyHubUuidDto.builder()
                .productUuid(savedProduct.getProductUuid())
                .productHubId(savedProduct.getProductHubId())
                .companyId(savedProduct.getCompanyId())
                .build());
      }
    } catch (Exception e) {
      log.error("Error deducting product quantity: {}", e.getMessage());
      throw new RuntimeException("Failed to deduct product quantities", e);
    }
    return responses;
  }
}
