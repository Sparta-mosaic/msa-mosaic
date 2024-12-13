package org.mosaic.hub.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mosaic.hub.application.dto.CreateHubRequest;
import org.mosaic.hub.application.dto.CreateHubResponse;
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
    CreateHubResponse response = hubCommandService.createHub(request);

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
}