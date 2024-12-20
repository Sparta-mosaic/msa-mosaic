package org.mosaic.ai.presentation.controller;

import static org.mosaic.ai.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.ai.libs.util.ApiResponseUtils.created;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mosaic.ai.application.service.GeminiService;
import org.mosaic.ai.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.ai.presentation.dtos.PromptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientGeminiController {

  @Autowired
  private GeminiService geminiService;

  @PostMapping("/api/v1/internal/ai/createResponse")
  public ResponseEntity<CommonResponse<String>> createResponse(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody PromptRequest prompt) {

    return created(geminiService
        .createResponse(prompt, userUuid));
  }

}
