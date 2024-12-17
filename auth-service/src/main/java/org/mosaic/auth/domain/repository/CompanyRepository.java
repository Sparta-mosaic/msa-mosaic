package org.mosaic.auth.domain.repository;

import java.util.Optional;
import org.mosaic.auth.domain.model.company.Company;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends QuerydslPredicateExecutor<Company> {

  Company save(Company company);

  Optional<Company> findByCompanyUUID(String companyUuid);
}
