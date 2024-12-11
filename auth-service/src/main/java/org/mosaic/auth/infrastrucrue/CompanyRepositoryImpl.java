package org.mosaic.auth.infrastrucrue;

import java.util.UUID;
import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositoryImpl extends JpaRepository<Company, UUID>,
    CompanyRepository {

}
