package org.mosaic.auth.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CompanyDto;
import org.mosaic.auth.application.dtos.CompanyResponse;
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

  @Transactional
  public CompanyResponse createCompany(CompanyDto request) {

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    Company company = Company.create(
        request.getId(),
        request.getCompanyName(),
        request.getCompanyAddress(),
        request.getCompanyType(),
        user,
        request.getHubId());

    return CompanyResponse.of(companyRepository.save(company));
  }

  @Transactional(readOnly = true)
  public CompanyResponse findCompanyById(UUID companyId) {
    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new RuntimeException("Company not found"));

    return CompanyResponse.of(company);
  }
}
