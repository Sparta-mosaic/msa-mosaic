package org.mosaic.hub.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mosaic.hub.application.dtos.HubResponse;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.repository.HubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Transactional
@SpringBootTest
class HubQueryServiceTest {

  @Autowired
  private HubQueryService hubQueryService;

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
  @DisplayName("getHub: 허브 uuid 를 받아 허브를 조회한다.")
  void getHub() {
  	// given
    final Hub hub = createHub();

  	// when
    HubResponse response = hubQueryService.getHub(hub.getUuid());

    // then
    assertThat(response.getHubUuid()).isEqualTo(hub.getUuid());
    assertThat(response.getManagerId()).isEqualTo(hub.getManagerId());
    assertThat(response.getName()).isEqualTo(hub.getName());
    assertThat(response.getAddress()).isEqualTo(hub.getAddress());
    assertThat(response.getLatitude()).isEqualTo(hub.getCoordinates().getLatitude());
    assertThat(response.getLongitude()).isEqualTo(hub.getCoordinates().getLongitude());
  }

  private Hub createHub() {
    return hubRepository.save(Hub.createHub(
        1L, "서울특별시 센터", "서울특별시 송파구 송파대로 55",
        37.456171545341, 126.70541564685742));
  }
}