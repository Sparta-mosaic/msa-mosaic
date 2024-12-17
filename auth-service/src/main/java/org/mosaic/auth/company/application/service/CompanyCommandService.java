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
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class CompanyCommandService {

  private final UserRepository userRepository;
  private final CompanyRepository companyRepository;


  public CompanyResponse createCompany(CompanyDto request) {

    // TODO: 허브 아이디 유효성 확인 필요

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Company company = Company.create(
        request.getId(),
        request.getName(),
        request.getAddress(),
        request.getCompanyType(),
        user,
        request.getHubId());

    return CompanyResponse.of(companyRepository.save(company));
  }

  public CompanyResponse updateCompany(UpdateCompanyDto request) {

    Company company = companyRepository.findById(request.getCompanyId())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.update(request.getName(), request.getAddress(),
        request.getCompanyType());

    return CompanyResponse.of(company);
  }

  public CompanyResponse updateCompanyHubId(UpdateCompanyHubIdDto request) {

    // TODO: 허브 아이디 유효성 확인 필요

    Company company = companyRepository.findById(request.getCompanyId())
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.updateHubId(request.getHubId());

    return CompanyResponse.of(company);
  }

  public void delete(Long companyId, CustomUserDetails userDetails) {

    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    company.delete(userDetails.getUserUuid());
  }
}
