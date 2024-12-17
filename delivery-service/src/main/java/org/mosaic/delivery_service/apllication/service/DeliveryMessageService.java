package org.mosaic.delivery_service.apllication.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.delivery_service.apllication.dtos.CompanyHubUuidDto;
import org.mosaic.delivery_service.domain.entity.delivery.Delivery;
import org.mosaic.delivery_service.domain.entity.delivery.DeliveryInfo;
import org.mosaic.delivery_service.domain.entity.delivery.DeliveryTotalTimeDistance;
import org.mosaic.delivery_service.domain.entity.delivery.ShippingInfo;
import org.mosaic.delivery_service.domain.entity.deliveryRoute.DeliveryRouteInfo;
import org.mosaic.delivery_service.domain.repository.DeliveryRepository;
import org.mosaic.delivery_service.infrastructure.client.dtos.CompanyHubDto;
import org.mosaic.delivery_service.infrastructure.client.dtos.CompanyRequest;
import org.mosaic.delivery_service.infrastructure.client.dtos.DeliveryShippingResponse;
import org.mosaic.delivery_service.infrastructure.client.dtos.OrderInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryMessageService {

  private final DeliveryRepository deliveryRepository;
  private final DeliveryAuthClientService deliveryAuthClientService;
  private final DeliveryHubClientService deliveryHubClientService;
  private final DeliveryOrderClientService deliveryOrderClientService;

  public Delivery processDelivery(
      String orderId, String userId, List<CompanyHubUuidDto> companyHubUuidDtos) {

    OrderInfoDto orderInfoDto = deliveryOrderClientService.getOrderHubPaths(orderId);

    CompanyRequest companyRequest = deliveryAuthClientService.getCompanyRequest(orderInfoDto);

    DeliveryShippingResponse deliveryShippingResponse =
        deliveryAuthClientService.getDeliveryShipping(companyRequest);

    CompanyHubDto companyHubDto = deliveryShippingResponse.getCompanyHubDto();

    DeliveryInfo deliveryInfo =
        DeliveryInfo.builder()
            .orderDeliveryUuid(UUID.randomUUID().toString())
            .orderId(orderId)
            .departureHubId(companyHubDto.getDepartureHubId())
            .arrivalHubId(companyHubDto.getArrivalHubId())
            .build();

    // 이거 합쳐 줘야함.
    ShippingInfo shippingInfo = deliveryAuthClientService.getShippingInfo(deliveryShippingResponse);

    List<DeliveryRouteInfo> deliveryRouteInfos =
        deliveryHubClientService.createDeliveryRouteInfos(
            companyHubDto.getDepartureHubId(), companyHubDto.getArrivalHubId());

    DeliveryTotalTimeDistance deliveryTotalTimeDistance =
        deliveryHubClientService.getDeliveryTotalTimeDistance(
            companyHubDto.getDepartureHubId(), companyHubDto.getArrivalHubId());

    Delivery delivery =
        Delivery.builder()
            .deliveryInfo(deliveryInfo)
            .shippingInfo(shippingInfo)
            .deliveryTotalTimeDistance(deliveryTotalTimeDistance)
            .shippingStartDate(orderInfoDto.getOrderDate())
            .shippingEndDate(orderInfoDto.getOrderDate().plusDays(3))
            .build();

    for (DeliveryRouteInfo routeInfo : deliveryRouteInfos) {
      delivery.addRoute(routeInfo);
    }

    deliveryRepository.save(delivery);

    return delivery;
  }
}
