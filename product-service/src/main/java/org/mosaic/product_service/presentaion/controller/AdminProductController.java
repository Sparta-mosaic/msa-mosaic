package org.mosaic.product_service.presentaion.controller;

import static org.mosaic.product_service.libs.common.constant.HttpHeaderConstants.*;
import static org.mosaic.product_service.libs.util.ApiResponseUtils.*;

import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.service.ProductCommandService;
import org.mosaic.product_service.libs.util.MosaicResponse;
import org.mosaic.product_service.presentaion.dtos.CreateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

    private final ProductCommandService productCommandService;

    @PostMapping()
    public ResponseEntity<MosaicResponse<CreateProductResponse>> createProduct(
        @Valid @RequestBody CreateProductRequest req){
        CreateProductResponse createProductResponse =
            productCommandService.createProduct(req.toDto());
        return created(createProductResponse);
    }

    @DeleteMapping("/{productUuid}")
    public ResponseEntity<MosaicResponse<String>> deleteHub(
        @RequestHeader(HEADER_USER_ID) String userUuid,
        @PathVariable String productUuid) {
        productCommandService.deleteProduct(userUuid, productUuid);
        return noContent();
    }


}
