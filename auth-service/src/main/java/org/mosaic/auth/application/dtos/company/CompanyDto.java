package org.mosaic.auth.application.dtos.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyDto {

  private Long id;
  private String name;
  private String address;
  private CompanyType companyType;
  private String userUuid;
  private String hubUuid;

  public static CompanyDto create(String name, String address, CompanyType companyType, String  userUuid, String  hubUuid) {
    return CompanyDto.builder()
        .name(name)
        .address(address)
        .companyType(companyType)
        .userUuid(userUuid)
        .hubUuid(hubUuid)
        .build();
  }
}
