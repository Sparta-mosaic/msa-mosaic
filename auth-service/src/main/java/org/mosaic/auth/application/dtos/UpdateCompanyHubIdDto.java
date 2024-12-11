package org.mosaic.auth.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCompanyHubIdDto {

  private Long companyId;
  private Long hubId;

  public static UpdateCompanyHubIdDto create(Long companyId, Long hubId) {
    return UpdateCompanyHubIdDto.builder()
        .companyId(companyId)
        .hubId(hubId)
        .build();
  }

}
