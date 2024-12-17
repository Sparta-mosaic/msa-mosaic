package org.mosaic.auth.company.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.company.application.dtos.CompanyDto;
import org.mosaic.auth.company.application.dtos.CompanyResponse;
import org.mosaic.auth.company.application.dtos.HubResponse;
import org.mosaic.auth.company.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.company.application.dtos.UpdateCompanyHubIdDto;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.domain.repository.CompanyRepository;
import org.mosaic.auth.company.infrastructure.HubClient;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyCommandService {

  private final UserRepository userRepository;
  private final CompanyRepository companyRepository;
  private final HubClient hubClient;


  @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_HUB_MANAGER')")
  public CompanyResponse createCompany(CompanyDto request, CustomUserDetails userDetails) {

    HubResponse hubResponse = hubClient.getHub(request.getHubUuid());

    if(!hubResponse.getIsPublic()) {
      throw new CustomException(ExceptionStatus.INVALID_HUB_ID);
    }

    log.info("[CompanyCommandService] Get HubResponse >>>>>>>>>>>>>>>>>> {} ", hubResponse);

    User user = userRepository.findByUserUUID(request.getUserUuid())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Company company = Company.create(
        request.getId(),
        request.getName(),
        request.getAddress(),
        request.getCompanyType(),
        user,
        hubResponse.getHubId());
    company.createdBy(userDetails.getUserUuid());

    return CompanyResponse.of(companyRepository.save(company));
  }

  public CompanyResponse updateCompany(UpdateCompanyDto request, CustomUserDetails userDetails) {

    Company company = companyRepository.findByCompanyUUID(request.getCompanyUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    if(userDetails.getUserRole().equals(UserRole.COMPANY)
        && !company.getCompanyUUID().equals(userDetails.getUserUuid())) {

      throw new CustomException(ExceptionStatus.UNAUTHORIZED);
    }

    company.update(request.getName(), request.getAddress(),
        request.getCompanyType());
    company.updatedBy(userDetails.getUserUuid());

    return CompanyResponse.of(company);
  }

  public CompanyResponse updateCompanyHubId(UpdateCompanyHubIdDto request, CustomUserDetails userDetails) {

    HubResponse hubResponse = hubClient.getHub(request.getHubUuid());

    if(!hubResponse.getIsPublic()) {
      throw new CustomException(ExceptionStatus.INVALID_HUB_ID);
    }

    log.info("[CompanyCommandService] Get HubResponse >>>>>>>>>>>>>>>>>> {} ", hubResponse);

    Company company = companyRepository.findByCompanyUUID(request.getCompanyUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    if(userDetails.getUserRole().equals(UserRole.COMPANY)
        && !company.getCompanyUUID().equals(userDetails.getUserUuid())) {

      throw new CustomException(ExceptionStatus.UNAUTHORIZED);
    }

    company.updateHubId(hubResponse.getHubId());
    company.updatedBy(userDetails.getUserUuid());

    return CompanyResponse.of(company);
  }

  public void delete(String companyUuid, CustomUserDetails userDetails) {

    Company company = companyRepository.findByCompanyUUID(companyUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.delete(userDetails.getUserUuid());
  }
}
