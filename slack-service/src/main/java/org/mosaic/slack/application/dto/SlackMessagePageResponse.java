package org.mosaic.slack.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.springframework.data.domain.Page;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SlackMessagePageResponse {

  private SlackMessagePage messagePage;


  public static SlackMessagePageResponse of(Page<SlackMessageResponse> page) {
    return SlackMessagePageResponse.builder()
        .messagePage(new SlackMessagePage(page))
        .build();
  }
}
