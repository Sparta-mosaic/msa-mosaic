package org.mosaic.auth.company.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.company.application.dtos.CompanyDto;
import org.mosaic.auth.company.application.dtos.CompanyResponse;
import org.mosaic.auth.company.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.company.application.dtos.UpdateCompanyHubIdDto;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.domain.repository.CompanyRepository;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class CompanyCommandService {

  private final UserRepository userRepository;
  private final CompanyRepository companyRepository;


  @PreAuthorize("hasAnyRole('ROLE_MASTER', 'ROLE_HUB_MANAGER')")
  public CompanyResponse createCompany(CompanyDto request, CustomUserDetails userDetails) {

    // TODO: 허브 아이디 유효성 확인 필요

    User user = userRepository.findByUserUUID(request.getUserUuid())
        .orElseThrow(() -> new RuntimeException("User not found"));

//    Hub hub = getFeignHub(request.getHubUuid());

    Company company = Company.create(
        request.getId(),
        request.getName(),
        request.getAddress(),
        request.getCompanyType(),
        user,
        1L);
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

    // TODO: 허브 아이디 유효성 확인 필요

    Company company = companyRepository.findByCompanyUUID(request.getCompanyUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    if(userDetails.getUserRole().equals(UserRole.COMPANY)
        && !company.getCompanyUUID().equals(userDetails.getUserUuid())) {

      throw new CustomException(ExceptionStatus.UNAUTHORIZED);
    }

//    company.updateHubId(request.getHubId());
    company.updatedBy(userDetails.getUserUuid());

    return CompanyResponse.of(company);
  }

  public void delete(String companyUuid, CustomUserDetails userDetails) {

    Company company = companyRepository.findByCompanyUUID(companyUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.delete(userDetails.getUserUuid());
  }
}
