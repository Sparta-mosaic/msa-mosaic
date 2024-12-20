package org.mosaic.slack.presentations.controller;

import static org.mosaic.slack.libs.constant.HttpHeaderConstants.HEADER_USER_ID;
import static org.mosaic.slack.libs.util.ApiResponseUtils.noContent;
import static org.mosaic.slack.libs.util.ApiResponseUtils.ok;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.slack.application.dto.SlackMessagePageResponse;
import org.mosaic.slack.application.service.SlackCommandService;
import org.mosaic.slack.application.service.SlackQueryService;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.mosaic.slack.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/slack")
@RequiredArgsConstructor
public class SlackAdminController {

  private final SlackCommandService slackCommandService;
  private final SlackQueryService slackQueryService;

  @GetMapping("/{messageId}")
  public ResponseEntity<CommonResponse<String>> getMessage(
      @PathVariable("messageId") Long messageId) {

    return ok(slackQueryService
        .getMessageById(messageId));
  }

  @GetMapping
  public ResponseEntity<CommonResponse<SlackMessagePageResponse>> getMessage(
      @QuerydslPredicate(root = SlackMessages.class) Predicate predicate,
      @PageableDefault(sort = "slackId", direction = Sort.Direction.DESC) Pageable pageable
  ) {

    return ok(slackQueryService
        .findAllMessages(predicate, pageable));
  }


  @DeleteMapping("/{messageId}")
  public ResponseEntity<CommonResponse<Void>> deleteSlackMessage(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @PathVariable Long messageId) {
    slackCommandService.deleteSlackMessage(userUuid, messageId);
    return noContent();
  }



}
