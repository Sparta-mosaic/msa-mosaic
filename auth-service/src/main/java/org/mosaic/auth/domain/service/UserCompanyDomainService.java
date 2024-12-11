package org.mosaic.auth.domain.service;

import org.mosaic.auth.domain.entity.company.Company;
import org.mosaic.auth.domain.entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserCompanyDomainService {

  public void connectUserCompany(User user, Company company){
    company.updateCompanyUser(user);
  }
}
