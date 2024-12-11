package org.mosaic.auth.application.dtos;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyDto {

  private UUID id;
  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private UUID userId;
  private UUID hubId;

  public static CompanyDto create(String companyName, String companyAddress, CompanyType companyType, UUID userId, UUID hubId) {
    return CompanyDto.builder()
        .companyName(companyName)
        .companyAddress(companyAddress)
        .companyType(companyType)
        .userId(userId)
        .hubId(hubId)
        .build();
  }

}
