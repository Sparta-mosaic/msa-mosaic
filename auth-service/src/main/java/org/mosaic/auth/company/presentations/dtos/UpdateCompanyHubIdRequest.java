package org.mosaic.auth.company.presentations.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.company.application.dtos.UpdateCompanyHubIdDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCompanyHubIdRequest {

  @NotBlank
  private String companyUuid;
  @NotBlank
  private String hubUuid;

  public UpdateCompanyHubIdDto toDTO() {
    return UpdateCompanyHubIdDto.create(companyUuid, hubUuid);
  }

}
