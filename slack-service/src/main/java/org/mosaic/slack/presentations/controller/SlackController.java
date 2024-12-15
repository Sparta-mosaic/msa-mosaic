package org.mosaic.slack.presentations.controller;

import static org.mosaic.slack.libs.util.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import org.mosaic.slack.application.dto.UserFeignResponse;
import org.mosaic.slack.application.service.SlackService;
import org.mosaic.slack.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.slack.presentations.dto.SendMessageRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/slack")
@RequiredArgsConstructor
public class SlackController {

  private final SlackService slackService;

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResult<UserFeignResponse>> getUser(
      @PathVariable Long userId) {

    return new ResponseEntity<>(success(
        slackService.getUser(userId)),
        HttpStatus.OK);
  }

  @PostMapping("/message")
  public ResponseEntity<ApiResult<String>> sendMessageToSlack(
      @RequestBody SendMessageRequestDto request){

    slackService.sendMessageToSlack(request);

    return new ResponseEntity<>(success(
        "Send Slack Message Success !!"),
        HttpStatus.OK);
  }



}
