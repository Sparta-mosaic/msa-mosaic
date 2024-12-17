package org.mosaic.delivery_service.apllication.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.delivery_service.domain.entity.delivery.ShippingInfo;
import org.mosaic.delivery_service.infrastructure.client.AuthFeignClient;
import org.mosaic.delivery_service.infrastructure.client.dtos.CompanyRequest;
import org.mosaic.delivery_service.infrastructure.client.dtos.DeliveryShippingResponse;
import org.mosaic.delivery_service.infrastructure.client.dtos.OrderInfoDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAuthClientService {

  private final AuthFeignClient authFeignClient;

  public CompanyRequest getCompanyRequest(OrderInfoDto dto) {
    return CompanyRequest.builder()
        .receiverCompanyId(dto.getReceiverCompanyId())
        .supplierCompanyId(dto.getSupplierCompanyId())
        .build();
  }

  public DeliveryShippingResponse getDeliveryShipping(CompanyRequest companyRequest) {
    return authFeignClient.getDeliveryShipping(companyRequest);
  }

  public ShippingInfo getShippingInfo(DeliveryShippingResponse res) {
    return ShippingInfo.builder()
        .shippingAddress(res.getShippingInfo().getShippingAddress())
        .shippingManagerId(res.getShippingInfo().getShippingManagerId())
        .build();
  }
}
