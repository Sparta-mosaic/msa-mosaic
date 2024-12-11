package org.mosaic.auth.application.dtos;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.entity.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyDto {

  private Long id;
  private String companyName;
  private String companyAddress;
  private CompanyType companyType;
  private Long userId;
  private Long hubId;

  public static CompanyDto create(String companyName, String companyAddress, CompanyType companyType, Long userId, Long hubId) {
    return CompanyDto.builder()
        .companyName(companyName)
        .companyAddress(companyAddress)
        .companyType(companyType)
        .userId(userId)
        .hubId(hubId)
        .build();
  }

}
