package org.mosaic.auth.company.presentations.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.company.application.dtos.CompanyDto;
import org.mosaic.auth.company.domain.entity.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCompanyRequest {

  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotBlank
  private CompanyType companyType;
  @NotBlank
  private Long userId;
  @NotBlank
  private Long hubId;

  public CompanyDto toDTO() {
    return CompanyDto.create(name, address, companyType, userId, hubId);
  }

}
