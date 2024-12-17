package org.mosaic.auth.company.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCompanyHubIdDto {

  private String  companyUuid;
  private String  hubUuid;

  public static UpdateCompanyHubIdDto create(String companyUuid, String  hubUuid) {
    return UpdateCompanyHubIdDto.builder()
        .companyUuid(companyUuid)
        .hubUuid(hubUuid)
        .build();
  }

}
