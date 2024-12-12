package org.mosaic.auth.company.application.service;

import static org.mosaic.auth.company.domain.entity.company.QCompany.company;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.company.application.dtos.CompanyDto;
import org.mosaic.auth.company.application.dtos.CompanyPageResponse;
import org.mosaic.auth.company.application.dtos.CompanyResponse;
import org.mosaic.auth.company.application.dtos.UpdateCompanyDto;
import org.mosaic.auth.company.application.dtos.UpdateCompanyHubIdDto;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.domain.repository.CompanyRepository;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @Transactional(readOnly = true)
  public CompanyPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    booleanBuilder.and(company.isPublic.eq(true));
    Page<Company> companyEntityPage = companyRepository.findAll(booleanBuilder, pageable);
    return CompanyPageResponse.of(companyEntityPage);

  }
}
