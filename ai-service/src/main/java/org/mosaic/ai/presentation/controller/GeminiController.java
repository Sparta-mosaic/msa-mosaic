package org.mosaic.ai.presentation.controller;

import static org.mosaic.ai.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.ai.libs.util.ApiResponseUtils.created;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.ai.application.service.GeminiService;
import org.mosaic.ai.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.ai.presentation.dtos.PromptRequest;
import org.mosaic.ai.presentation.dtos.RequestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GeminiController {

  private final GeminiService geminiService;

  @PostMapping("/api/v1/ai")
  public ResponseEntity<CommonResponse<String>> createResponse(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody PromptRequest prompt) {

    return created(geminiService
        .createResponse(prompt, userUuid));
  }

  @PostMapping("/api/v1/ai/template")
  public ResponseEntity<CommonResponse<String>> createPromptTemplate(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody RequestTemplate template) {
    geminiService.createPromptTemplate(template, userUuid);

    return created(
        "Template Saved Successfully !!");
  }
}