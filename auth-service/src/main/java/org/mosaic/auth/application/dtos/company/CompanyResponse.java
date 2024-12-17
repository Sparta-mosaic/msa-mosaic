package org.mosaic.auth.application.dtos.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.domain.model.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyResponse {

  private Long companyId;
  private String companyUuid;
  private String name;
  private String address;
  private CompanyType companyType;
  private Long userId;
  private String hubUuid;

  public static CompanyResponse of(Company company){
    return CompanyResponse.builder()
        .companyId(company.getCompanyId())
        .companyUuid(company.getCompanyUUID())
        .name(company.getCompanyName())
        .address(company.getCompanyAddress())
        .companyType(company.getCompanyType())
        .userId(company.getUser().getUserId())
        .hubUuid(company.getHubUuid())
        .build();
  }
}
