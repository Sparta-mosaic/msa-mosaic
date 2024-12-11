package org.mosaic.auth.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyResponse {

  private Long companyId;
  private String name;
  private String address;
  private CompanyType companyType;
  private Long userId;
  private Long hubId;

  public static CompanyResponse of(Company company){
    return CompanyResponse.builder()
        .companyId(company.getCompanyId())
        .name(company.getCompanyName())
        .address(company.getCompanyAddress())
        .companyType(company.getCompanyType())
        .userId(company.getUser().getUserId())
        .hubId(company.getHubId())
        .build();
  }
}
