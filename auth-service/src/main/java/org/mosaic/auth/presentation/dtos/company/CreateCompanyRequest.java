package org.mosaic.auth.presentation.dtos.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.application.dtos.company.CompanyDto;
import org.mosaic.auth.domain.model.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCompanyRequest {

  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotBlank
  private CompanyType companyType;
  @NotBlank
  private String userUuid;
  @NotBlank
  private String hubUuid;

  public CompanyDto toDTO() {
    return CompanyDto.create(name, address, companyType, userUuid, hubUuid);
  }
}
