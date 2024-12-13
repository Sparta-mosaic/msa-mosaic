package org.mosaic.auth.company.presentations.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.company.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.company.domain.entity.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCompanyRequest {

  @NotBlank
  private Long companyId;
  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotBlank
  private CompanyType companyType;

  public UpdateCompanyDto toDTO() {
    return UpdateCompanyDto.create(companyId, name, address, companyType);
  }

}
