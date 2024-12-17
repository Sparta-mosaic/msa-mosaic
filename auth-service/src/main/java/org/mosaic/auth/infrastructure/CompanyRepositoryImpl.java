package org.mosaic.auth.infrastructure;


import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositoryImpl extends JpaRepository<Company, Long>,
    CompanyRepository {

}
