package org.mosaic.auth.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCompanyDto {

  private Long companyId;
  private String name;
  private String address;
  private CompanyType companyType;

  public static UpdateCompanyDto create(Long id, String name, String address,
      CompanyType companyType) {
    return UpdateCompanyDto.builder()
        .companyId(id)
        .name(name)
        .address(address)
        .companyType(companyType)
        .build();
  }

}
