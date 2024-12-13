package org.mosaic.hub.presentation.controller;

import static org.mosaic.hub.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mosaic.hub.application.dto.CreateHubRequest;
import org.mosaic.hub.application.dto.UpdateHubRequest;
import org.mosaic.hub.application.service.HubCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminHubController.class)
class AdminHubControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private HubCommandService hubCommandService;

  @Test
  @DisplayName("createHub: 허브 정보를 받아 허브를 생성한다.")
  void createHub() throws Exception {
    // given
    final String uri = "/api/v1/admin/hubs";

    final Long mangerId = 1L;
    final String name = "서울특별시 센터";
    final String address = "서울특별시 송파구 송파대로 55";
    final double latitude = 37.456171545341;
    final double longitude = 126.70541564685742;
    final CreateHubRequest request =
        new CreateHubRequest(mangerId, name, address, latitude, longitude);

    // expected
    mockMvc.perform(post(uri)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.success").value(true));
  }

  @Test
  @DisplayName("updateHub: 허브 수정 정보를 받아 허브를 수정한다.")
  void updateHub() throws Exception {
    // given
    final String uri = "/api/v1/admin/hubs/{hubUuid}";
    final String hubUuid = "e1b84264-b95b-4a53-b7b9-3fba65bd784e";

    final Long managerId = 1L;
    final String name = "서울특별시 센터";
    final String address = "서울특별시 송파구 송파대로 55";
    final double latitude = 37.456171545341;
    final double longitude = 126.70541564685742;
    final UpdateHubRequest request =
        new UpdateHubRequest(managerId, name, address, latitude, longitude);

    // expected
    mockMvc.perform(put(uri, hubUuid)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));
  }

  @Test
  @DisplayName("deleteHub: 허브 아이디를 받아 허브를 삭제한다.")
  void deleteHub() throws Exception {
    // given
    final String uri = "/api/v1/admin/hubs/{hubUuid}";
    final String hubUuid = "e1b84264-b95b-4a53-b7b9-3fba65bd784e";

    // expected
    mockMvc.perform(delete(uri, hubUuid)
            .header(HEADER_USER_ID, UUID.randomUUID().toString()))
        .andDo(print())
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$.success").value(true));
  }
}