package org.mosaic.auth.presentations.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.application.dtos.CompanyDto;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCompanyRequest {

  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private Long userId;
  private Long hubId;

  public CompanyDto toDTO() {
    return CompanyDto.create(companyName, companyAddress, companyType, userId, hubId);
  }

}
