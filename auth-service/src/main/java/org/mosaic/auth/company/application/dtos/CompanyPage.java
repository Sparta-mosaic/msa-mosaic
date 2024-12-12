package org.mosaic.auth.company.application.dtos;

import lombok.Getter;
import lombok.ToString;
import org.mosaic.auth.company.domain.entity.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class CompanyPage extends PagedModel<Company> {

  public CompanyPage(Page<Company> companyPage) {
    super(
        new PageImpl<>(
            companyPage.getContent(),
            companyPage.getPageable(),
            companyPage.getTotalElements()
        )
    );
  }
}
