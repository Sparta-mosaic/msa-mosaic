package org.mosaic.auth.application.service.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.application.dtos.company.CompanyDto;
import org.mosaic.auth.application.dtos.company.CompanyResponse;
import org.mosaic.auth.application.dtos.company.HubResponse;
import org.mosaic.auth.application.dtos.company.UpdateCompanyDto;
import org.mosaic.auth.application.dtos.company.UpdateCompanyHubIdDto;
import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.mosaic.auth.infrastructure.HubClient;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.domain.model.user.UserRole;
import org.mosaic.auth.domain.repository.UserRepository;
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

  public CompanyResponse createCompany(CompanyDto request, CustomUserDetails userDetails) {

    HubResponse hubResponse = hubClient.getHub(request.getHubUuid()).getResponse();
    Long hubId = hubResponse.getHubId();

    if(hubId == null) {
      throw new CustomException(ExceptionStatus.INVALID_HUB_ID);
    }

    User user = userRepository.findByUserUUID(request.getUserUuid())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Company company = Company.create(
        request.getId(),
        request.getName(),
        request.getAddress(),
        request.getCompanyType(),
        user,
        hubResponse.getHubUuid());
    company.createdBy(userDetails.getUserUuid());

    return CompanyResponse.of(companyRepository.save(company));
  }

  public CompanyResponse updateCompany(UpdateCompanyDto request, CustomUserDetails userDetails) {

    Company company = companyRepository.findByCompanyUUID(request.getCompanyUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    if(userDetails.getUserRole().equals(UserRole.ROLE_COMPANY)
        && !company.getCompanyUUID().equals(userDetails.getUserUuid())) {

      throw new CustomException(ExceptionStatus.UNAUTHORIZED);
    }

    company.update(request.getName(), request.getAddress(),
        request.getCompanyType());
    company.updatedBy(userDetails.getUserUuid());

    return CompanyResponse.of(company);
  }

  public CompanyResponse updateCompanyHubId(UpdateCompanyHubIdDto request, CustomUserDetails userDetails) {

    HubResponse hubResponse = hubClient.getHub(request.getHubUuid()).getResponse();
    Long hubId = hubResponse.getHubId();

    if(hubId == null) {
      throw new CustomException(ExceptionStatus.INVALID_HUB_ID);
    }


    Company company = companyRepository.findByCompanyUUID(request.getCompanyUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    if(userDetails.getUserRole().equals(UserRole.ROLE_COMPANY)
        && !company.getCompanyUUID().equals(userDetails.getUserUuid())) {

      throw new CustomException(ExceptionStatus.UNAUTHORIZED);
    }

    company.updateHubId(hubResponse.getHubUuid());
    company.updatedBy(userDetails.getUserUuid());

    return CompanyResponse.of(company);
  }

  public void delete(String companyUuid, CustomUserDetails userDetails) {

    Company company = companyRepository.findByCompanyUUID(companyUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.delete(userDetails.getUserUuid());
  }
}
