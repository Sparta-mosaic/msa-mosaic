package org.mosaic.ai.presentation.controller;

import static org.mosaic.ai.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.ai.libs.util.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.ai.application.service.GeminiService;
import org.mosaic.ai.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.ai.presentation.dtos.RequestPrompt;
import org.mosaic.ai.presentation.dtos.RequestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class GeminiController {

  private final GeminiService geminiService;
  @PostMapping("/api/v1/ai/response")
  public ResponseEntity<ApiResult<String>> estimatedTimeOfDeparture(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody RequestPrompt prompt) {

    return new ResponseEntity<>(success(
        geminiService.getContents(prompt, userUuid)),
        HttpStatus.OK);
  }

  @PostMapping("/api/v1/ai/template")
  public ResponseEntity<ApiResult<String>> registPromptTemplate(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody RequestTemplate template) {
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>어디야.>>>>>>>>>>>>>>>>>>>>>>");
    geminiService.registPromptTemplate(template, userUuid);

    return new ResponseEntity<>(success(
        "Template Saved Successfully !!"),
        HttpStatus.OK);
  }
}