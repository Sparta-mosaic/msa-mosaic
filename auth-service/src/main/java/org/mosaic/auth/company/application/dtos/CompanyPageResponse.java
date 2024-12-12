package org.mosaic.auth.company.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.springframework.data.domain.Page;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyPageResponse {

  private CompanyPage companyPage;

  public static CompanyPageResponse of(Page<Company> companyPage) {
    return CompanyPageResponse.builder()
        .companyPage(new CompanyPage(companyPage))
        .build();
  }

}
