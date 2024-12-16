package org.mosaic.slack.application.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.slack.domain.entity.SlackMessages;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SlackMessageResponse {

  private Long messageId;
  private String slackId;
  private String userEmail;
  private String text;
  private LocalDateTime createdAt;
  private String createdby;

  public static SlackMessageResponse of(SlackMessages message) {
    return SlackMessageResponse.builder()
        .messageId(message.getSlackMessageId())
        .slackId(message.getReceiverSlackId())
        .userEmail(message.getReceiverSlackEmail())
        .text(message.getText())
        .createdAt(message.getCreatedAt())
        .createdby(message.getCreatedBy())
        .build();
  }
}
