package org.mosaic.auth.presentations.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.application.dtos.UpdateCompanyHubIdDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCompanyHubIdRequest {

  private Long companyId;
  private Long hubId;

  public UpdateCompanyHubIdDto toDTO() {
    return UpdateCompanyHubIdDto.create(companyId, hubId);
  }

}
