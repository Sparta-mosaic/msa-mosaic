package org.mosaic.product_service.presentaion.controller;

import static org.mosaic.product_service.libs.common.constant.HttpHeaderConstants.*;
import static org.mosaic.product_service.libs.util.ApiResponseUtils.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.dtos.ProductResponse;
import org.mosaic.product_service.application.dtos.UpdateProductResponse;
import org.mosaic.product_service.application.service.ProductCommandService;
import org.mosaic.product_service.application.service.ProductQueryService;
import org.mosaic.product_service.libs.util.MosaicResponse;
import org.mosaic.product_service.presentaion.dtos.CreateProductRequest;
import org.mosaic.product_service.presentaion.dtos.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

  private final ProductCommandService productCommandService;
  private final ProductQueryService productQueryService;

  @PostMapping()
  public ResponseEntity<MosaicResponse<CreateProductResponse>> createProduct(
      @Valid @RequestBody CreateProductRequest req) {
    return created(productCommandService.createProduct(req.toDto()));
  }

  @DeleteMapping("/{productUuid}")
  public ResponseEntity<MosaicResponse<String>> deleteHub(
      @RequestHeader(HEADER_USER_ID) String userUuid, @PathVariable String productUuid) {
    productCommandService.deleteProduct(userUuid, productUuid);
    return noContent();
  }

  @PutMapping("/{productUuid}")
  public ResponseEntity<MosaicResponse<UpdateProductResponse>> updateProduct(
      @PathVariable String productUuid, @Valid @RequestBody UpdateProductRequest req) {
    return ok(productCommandService.updateProduct(productUuid, req.toDto()));
  }

  @GetMapping("/{productUuid}")
  public ResponseEntity<MosaicResponse<ProductResponse>> getProduct(
      @PathVariable String productUuid) {
    return ok(productQueryService.getProductResponse(productUuid));
  }
}
