package org.mosaic.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CompanyDto;
import org.mosaic.auth.application.dtos.CompanyResponse;
import org.mosaic.auth.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.application.dtos.UpdateCompanyHubIdDto;
import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.entity.user.User;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.mosaic.auth.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final UserRepository userRepository;
  private final CompanyRepository companyRepository;

  @Transactional(readOnly = true)
  public CompanyResponse findCompanyById(Long companyId) {
    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new RuntimeException("Company not found"));

    return CompanyResponse.of(company);
  }

  @Transactional
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

  @Transactional
  public CompanyResponse updateCompany(UpdateCompanyDto request) {

    Company company = companyRepository.findById(request.getCompanyId())
        .orElseThrow(() -> new RuntimeException("Company not found"));

    company.update(request.getName(), request.getAddress(),
        request.getCompanyType());

    return CompanyResponse.of(company);
  }

  @Transactional
  public CompanyResponse updateCompanyHubId(UpdateCompanyHubIdDto request) {

    // TODO: 허브 아이디 유효성 확인 필요

    Company company = companyRepository.findById(request.getCompanyId())
        .orElseThrow(() -> new RuntimeException("Company not found"));

    company.updateHubId(request.getHubId());

    return CompanyResponse.of(company);
  }

  @Transactional
  public void delete(Long companyId) {

    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new RuntimeException("Company not found"));

    company.delete();
  }
}
