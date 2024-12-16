package org.mosaic.product_service.application.dtos;

import lombok.Getter;

@Getter
public class ProductDeductDto {
  String productUuid;
  Long quantity;
}
