package org.mosaic.auth.company.presentations.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.company.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.company.domain.entity.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCompanyRequest {

  private Long companyId;
  private String name;
  private String address;
  private CompanyType companyType;

  public UpdateCompanyDto toDTO() {
    return UpdateCompanyDto.create(companyId, name, address, companyType);
  }

}
