package org.mosaic.auth.application.dtos.user;

import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.user.User;

@Getter
@Builder
public class UserFeignResponse {

  private Long userId;
  private String username;
  private String role;
  private String slackEmail;

  public static UserFeignResponse of(User user){
    return UserFeignResponse.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .role(user.getRole().toString())
        .slackEmail(user.getSlackEmail())
        .build();
  }
}
