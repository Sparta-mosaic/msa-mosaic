package org.mosaic.auth.company.domain.repository;

import java.util.Optional;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends QuerydslPredicateExecutor<Company> {

  Company save(Company company);

  Optional<Company> findById(Long companyId);
}
