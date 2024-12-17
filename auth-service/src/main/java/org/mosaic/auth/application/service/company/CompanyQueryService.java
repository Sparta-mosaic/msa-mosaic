package org.mosaic.auth.application.service.company;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.company.CompanyPageResponse;
import org.mosaic.auth.application.dtos.company.CompanyResponse;
import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyQueryService {

  private final CompanyRepository companyRepository;

  public CompanyResponse findCompanyByUuid(String companyUuid) {
    Company company = companyRepository.findByCompanyUUID(companyUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));

    return CompanyResponse.of(company);
  }

  public CompanyPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    Page<Company> companyEntityPage = companyRepository.findAll(booleanBuilder, pageable);
    Page<CompanyResponse> companyDtoPage = companyEntityPage
        .map(CompanyResponse::of);

    return CompanyPageResponse.of(companyDtoPage);

  }
}
