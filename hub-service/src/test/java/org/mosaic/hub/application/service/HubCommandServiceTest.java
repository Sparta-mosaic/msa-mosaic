package org.mosaic.hub.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mosaic.hub.application.dtos.CreateHubResponse;
import org.mosaic.hub.application.dtos.CreateHubTransferResponse;
import org.mosaic.hub.application.dtos.CreateHubTransferServiceRequest;
import org.mosaic.hub.application.dtos.HubTransferInfo;
import org.mosaic.hub.application.dtos.HubTransferResponse;
import org.mosaic.hub.application.dtos.UpdateHubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.mosaic.hub.presentation.dtos.CreateHubRequest;
import org.mosaic.hub.presentation.dtos.UpdateHubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Transactional
@SpringBootTest
class HubCommandServiceTest {

  @Autowired
  private HubCommandService hubCommandService;

  @Autowired
  private HubRepository hubRepository;

  @BeforeEach
  void setUp() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader(HEADER_USER_ID, "123e4567-e89b-12d3-a456-426614174000");
    RequestContextHolder.setRequestAttributes(
        new ServletRequestAttributes(request));
  }

  @AfterEach
  void tearDown() {
    RequestContextHolder.resetRequestAttributes();
  }

  @Test
  @DisplayName("createHub: 허브 정보를 받아 허브를 생성한다.")
  void createHub_success() {
    // given
    final Long managerId = 1L;
    final String name = "서울특별시 센터";
    final String address = "서울특별시 송파구 송파대로 55";
    final double latitude = 37.456171545341;
    final double longitude = 126.70541564685742;
    final CreateHubRequest request =
        new CreateHubRequest(managerId, name, address, latitude, longitude);

    // when
    CreateHubResponse response = hubCommandService.createHub(
        request.toService());

    // then
    assertThat(response.getHubId()).isNotNull();
    assertThat(response)
        .extracting("managerId", "name", "address", "latitude", "longitude",
            "createdAt", "createdBy", "isPublic")
        .containsExactly(
            response.getManagerId(), response.getName(), response.getAddress(),
            response.getLatitude(), response.getLongitude(),
            response.getCreatedAt(), response.getCreatedBy(),
            response.isPublic());
  }

  @Test
  @DisplayName("updateHub: 허브 수정 정보를 받아 허브를 수정한다.")
  void updateHub_success() {
    // given
    final Hub hub = create(1L, "서울특별시 센터", "서울특별시 송파구 송파대로 55", 37.456171545341,
        126.70541564685742);

    final Long managerId = 1L;
    final String name = "서울특별시 센터 수정";
    final String address = "서울특별시 송파구 송파대로 55-1";
    final double latitude = 37.556171545341;
    final double longitude = 126.80541564685742;
    final UpdateHubRequest request =
        new UpdateHubRequest(managerId, name, address, latitude, longitude);

    // when
    UpdateHubResponse response = hubCommandService.updateHub(hub.getUuid(),
        request.toService());

    // then
    assertThat(response.getHubId()).isNotNull();
    assertThat(response)
        .extracting(
            "managerId", "name", "address", "latitude", "longitude",
            "createdAt", "createdBy", "updatedAt", "updatedBy", "isPublic")
        .containsExactly(
            response.getManagerId(), response.getName(), response.getAddress(),
            response.getLatitude(), response.getLongitude(),
            response.getCreatedAt(), response.getCreatedBy(),
            response.getUpdatedAt(), response.getUpdatedBy(),
            response.isPublic());
  }

  @Test
  @DisplayName("deleteHub: 허브 아이디를 받아 허브를 삭제한다.")
  void deleteHub_success() {
    // given
    final Hub hub = create(1L, "서울특별시 센터", "서울특별시 송파구 송파대로 55", 37.456171545341,
        126.70541564685742);
    final String userUuid = UUID.randomUUID().toString();

    // when
    hubCommandService.deleteHub(userUuid, hub.getUuid());

    // then
    assertThat(hub.isDeleted()).isTrue();
    assertThat(hub.getDeletedBy()).isEqualTo(userUuid);
  }

  @Test
  @DisplayName("createHubTransfer: 허브 이동 정보를 받아 생성한다.")
  void createHubTransfer() {
    // given
    final Hub departureHub = create(1L, "서울특별시 센터", "서울특별시 송파구 송파대로 55",
        37.456171545341, 126.70541564685742);
    final Hub arrivalHub = create(1L, "경기 북부 센터", "경기도 고양시 덕양구 권율대로 570",
        37.640653137831514, 126.87359977084844);
    final Hub arrivalHub2 = create(1L, "경기 남부 센터", "경기도 이천시 덕평로 257-21",
        37.19060194448469, 127.3758115903835);

    final int estimatedTime = 67;
    final double estimatedDistance = 102;
    final HubTransferInfo hubTransferInfo = new HubTransferInfo(
        arrivalHub.getUuid(), estimatedTime, estimatedDistance);

    final int estimatedTime2 = 77;
    final double estimatedDistance2 = 112;
    final HubTransferInfo hubTransferInfo2 = new HubTransferInfo(
        arrivalHub2.getUuid(), estimatedTime2, estimatedDistance2);

    final List<HubTransferInfo> hubTransferInfoList = List.of(
        hubTransferInfo, hubTransferInfo2);
    final CreateHubTransferServiceRequest request = new CreateHubTransferServiceRequest(
        hubTransferInfoList);

    // when
    CreateHubTransferResponse response = hubCommandService.createHubTransfer(
        departureHub.getUuid(), request);

    // then
    assertThat(response.getHubTransfers())
        .hasSize(hubTransferInfoList.size())
        .extracting(
            HubTransferResponse::getDepartureHubName,
            HubTransferResponse::getArrivalHubName,
            HubTransferResponse::getEstimatedTime,
            HubTransferResponse::getEstimatedDistance)
        .containsExactly(
            tuple(departureHub.getName(), arrivalHub.getName(),
                hubTransferInfo.getEstimatedTime(),
                hubTransferInfo.getEstimatedDistance()),
            tuple(departureHub.getName(), arrivalHub2.getName(),
                hubTransferInfo2.getEstimatedTime(),
                hubTransferInfo2.getEstimatedDistance()));
  }

  private Hub create(long managerId, String name, String address,
      double latitude, double longitude) {
    return hubRepository.save(Hub.create(
        managerId, name, address, latitude, longitude));
  }
}