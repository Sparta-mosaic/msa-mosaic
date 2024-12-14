package org.mosaic.product_service.presentaion.controller;

import static org.mosaic.product_service.libs.util.ApiResponseUtils.*;

import org.mosaic.product_service.application.dtos.CreateProductResponse;
import org.mosaic.product_service.application.service.ProductMethodService;
import org.mosaic.product_service.libs.util.MosaicResponse;
import org.mosaic.product_service.presentaion.dtos.CreateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class AdminProductController {

    private final ProductMethodService productMethodService;

    @PostMapping()
    public ResponseEntity<MosaicResponse<CreateProductResponse>> createProduct(
        @Valid @RequestBody CreateProductRequest req){
        CreateProductResponse createProductResponse =
            productMethodService.createProduct(req.toDto());
        return created(createProductResponse);
    }

}
