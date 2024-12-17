package org.mosaic.auth.libs.common;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.domain.model.company.Company;
import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.domain.repository.CompanyRepository;
import org.mosaic.auth.libs.common.dtos.CompanyHubDto;
import org.mosaic.auth.libs.common.dtos.CompanyRequest;
import org.mosaic.auth.libs.common.dtos.DeliveryShippingResponse;
import org.mosaic.auth.libs.common.dtos.ShippingInfo;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryClientService {
  
  private final CompanyRepository companyRepository;

  public DeliveryShippingResponse getDeliveryShipping(CompanyRequest req) {
    return DeliveryShippingResponse.builder()
        .companyHubDto(getCompanyDto(req))
        .shippingInfo(getShippingInfo(req.getReceiverCompanyId()))
        .build();
  }

  private CompanyHubDto getCompanyDto(CompanyRequest req) {
    String supplierHubId = getCompany(req.getSupplierCompanyId()).getHubUuid();
    String receiverHubId = getCompany(req.getReceiverCompanyId()).getHubUuid();

    return CompanyHubDto.builder()
        .departureHubId(supplierHubId)
        .arrivalHubId(receiverHubId)
        .build();
  }

  private ShippingInfo getShippingInfo(String receiverCompanyId) {
    Company receiverCompany = getCompany(receiverCompanyId);
    User receiverCompanyUser = receiverCompany.getUser();

    return ShippingInfo.builder()
        .shippingAddress(receiverCompany.getCompanyAddress())
        .shippingManagerSlackId(receiverCompanyUser.getSlackEmail())
        .shippingManagerId(receiverCompanyUser.getUserUUID())
        .build();
  }

  private Company getCompany(String req) {
    return companyRepository
        .findByCompanyUUID(req)
        .orElseThrow(() -> new CustomException(ExceptionStatus.COMPANY_NOT_FOUND));
  }
}
