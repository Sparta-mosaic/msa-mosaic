package org.mosaic.auth.presentations.dtos;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mosaic.auth.application.dtos.CompanyDto;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@AllArgsConstructor
public class CreateCompanyRequest {

  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private UUID userId;
  private UUID hubId;

  public CompanyDto toDTO() {
    return CompanyDto.create(companyName, companyAddress, companyType, userId, hubId);
  }

}
