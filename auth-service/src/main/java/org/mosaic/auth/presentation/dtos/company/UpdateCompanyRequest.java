package org.mosaic.auth.presentation.dtos.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.application.dtos.company.UpdateCompanyDto;
import org.mosaic.auth.domain.model.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCompanyRequest {

  @NotBlank
  private String companyUuid;
  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotBlank
  private CompanyType companyType;

  public UpdateCompanyDto toDTO() {
    return UpdateCompanyDto.create(companyUuid, name, address, companyType);
  }
}
