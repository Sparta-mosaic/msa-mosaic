package org.mosaic.auth.company.infrastructure;


import org.mosaic.auth.company.domain.entity.company.Company;
import org.mosaic.auth.company.domain.repository.CompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositoryImpl extends JpaRepository<Company, Long>,
    CompanyRepository {

}
