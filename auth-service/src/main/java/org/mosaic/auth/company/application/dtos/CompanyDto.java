package org.mosaic.auth.company.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.company.domain.entity.company.CompanyType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CompanyDto {

  private Long id;
  private String name;
  private String address;
  private CompanyType companyType;
  private Long userId;
  private Long hubId;

  public static CompanyDto create(String name, String address, CompanyType companyType, Long userId, Long hubId) {
    return CompanyDto.builder()
        .name(name)
        .address(address)
        .companyType(companyType)
        .userId(userId)
        .hubId(hubId)
        .build();
  }

}
