package org.mosaic.auth.application.dtos;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyResponse {

  private Long id;
  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private Long userId;
  private Long hubId;

  public static CompanyResponse of(Company company){
    return CompanyResponse.builder()
        .id(company.getCompanyId())
        .companyName(company.getCompanyName())
        .companyAddress(company.getCompanyAddress())
        .companyType(company.getCompanyType())
        .hubId(company.getHubId())
        .build();
  }
}
