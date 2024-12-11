package org.mosaic.auth.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.mosaic.auth.domain.entity.company.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository {

  Company save(Company company);

  Optional<Company> findById(UUID companyId);
}
