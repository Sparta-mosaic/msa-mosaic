package org.mosaic.slack.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientUserResponse {

  private Long userId;
  private String username;
  private String role;
  private String slackEmail;

}
