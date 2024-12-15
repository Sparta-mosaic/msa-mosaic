package org.mosaic.product_service.presentaion.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.product_service.application.dtos.UpdateProductDto;

@Getter
@AllArgsConstructor
public class UpdateProductRequest {
  private String companyId;
  private String productHubId;
  @NotBlank private String productName;

  @NotBlank
  @Pattern(regexp = "^\\d+$", message = "숫자만 입력 가능합니다")
  private String productPrice;

  @NotBlank private String productDescription;

  @NotBlank
  @Pattern(regexp = "^\\d+$", message = "숫자만 입력 가능합니다")
  private String stockQuantity;

  public UpdateProductDto toDto() {
    return UpdateProductDto.create(
        this.companyId,
        this.productHubId,
        this.productName,
        this.productPrice,
        this.productDescription,
        this.stockQuantity);
  }
}
