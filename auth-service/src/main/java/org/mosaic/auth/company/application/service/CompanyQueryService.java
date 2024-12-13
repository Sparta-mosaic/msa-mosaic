package org.mosaic.auth.company.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.company.application.dtos.CompanyPageResponse;
import org.mosaic.auth.company.application.dtos.CompanyResponse;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.domain.repository.CompanyRepository;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyQueryService {

  private final UserRepository userRepository;
  private final CompanyRepository companyRepository;

  public CompanyResponse findCompanyById(Long companyId) {
    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new RuntimeException("Company not found"));

    return CompanyResponse.of(company);
  }

  public CompanyPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    Page<Company> companyEntityPage = companyRepository.findAll(booleanBuilder, pageable);
    return CompanyPageResponse.of(companyEntityPage);

  }
}
