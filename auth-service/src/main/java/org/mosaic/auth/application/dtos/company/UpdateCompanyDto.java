package org.mosaic.auth.application.dtos.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCompanyDto {

  private String companyUuid;
  private String name;
  private String address;
  private CompanyType companyType;

  public static UpdateCompanyDto create(String companyUuid, String name, String address,
      CompanyType companyType) {
    return UpdateCompanyDto.builder()
        .companyType(companyType)
        .name(name)
        .address(address)
        .companyType(companyType)
        .build();
  }
}
