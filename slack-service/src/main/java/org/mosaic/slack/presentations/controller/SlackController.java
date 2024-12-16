package org.mosaic.slack.presentations.controller;

import static org.mosaic.slack.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.slack.libs.util.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import org.mosaic.slack.application.service.SlackCommandService;
import org.mosaic.slack.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.slack.presentations.dto.CreateMessageRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/slack")
@RequiredArgsConstructor
public class SlackController {

  private final SlackCommandService slackCommandService;

  @PostMapping("/message")
  public ResponseEntity<ApiResult<String>> createAndSendMessage(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody CreateMessageRequestDto request){

    slackCommandService.createAndSendMessage(request);

    return new ResponseEntity<>(success(
        "Send Slack Message Success !!"),
        HttpStatus.OK);
  }



}
