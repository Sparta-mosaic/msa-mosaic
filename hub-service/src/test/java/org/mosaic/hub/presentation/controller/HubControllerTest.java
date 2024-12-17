package org.mosaic.hub.presentation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mosaic.hub.application.service.HubQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HubController.class)
class HubControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private HubQueryService hubQueryService;

  @Test
  @DisplayName("getHub: 허브 uuid 를 받아 허브를 조회한다.")
  void getHub() throws Exception {
  	// given
    final String uri = "/api/v1/hubs/{hubUuid}";
    final String hubUuid = "e1b84264-b95b-4a53-b7b9-3fba65bd784e";

  	// expected
    mockMvc.perform(get(uri, hubUuid))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));
  }

  @Test
  @DisplayName("searchHub: 허브 검색 정보를 받아 허브를 조회한다.")
  void searchHub() throws Exception {
    // given
    final String uri = "/api/v1/hubs";
    final String name = "서울특별시 센터";

  	// expected
    mockMvc.perform(get(uri).queryParam("name", name))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));
  }
  
  @Test
  @DisplayName("getHubPath: 출발 허브 UUID, 도착 허브 UUID 를 입력받아 허브 경로를 조회한다.")
  void getHubPath() throws Exception {
  	// given
    final String uri = "/api/v1/hub-paths";
    final String departureHubUuid = UUID.randomUUID().toString();
    final String arrivalHubUuid = UUID.randomUUID().toString();

    // expected
    mockMvc.perform(get(uri)
            .queryParam("departureHubUuid", departureHubUuid)
            .queryParam("arrivalHubUuid", arrivalHubUuid))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));
  }
  
}