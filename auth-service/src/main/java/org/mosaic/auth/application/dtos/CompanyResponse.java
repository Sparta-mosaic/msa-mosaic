package org.mosaic.auth.application.dtos;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CompanyResponse {

  private UUID id;
  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private UUID userId;
  private UUID hubId;

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
