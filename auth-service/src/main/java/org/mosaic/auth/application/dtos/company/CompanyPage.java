package org.mosaic.auth.application.dtos.company;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class CompanyPage extends PagedModel<CompanyResponse> {

  public CompanyPage(Page<CompanyResponse> companyPage) {
    super(
        new PageImpl<>(
            companyPage.getContent(),
            companyPage.getPageable(),
            companyPage.getTotalElements()
        )
    );
  }
}
