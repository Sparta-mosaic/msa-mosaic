package org.mosaic.auth.user.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.mosaic.auth.user.domain.entity.user.User;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserResponse {

  private Long userId;
  private String username;
  private UserRole role;
  private String slackEmail;

  public static UserResponse of(User user){
    return UserResponse.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .role(user.getRole())
        .slackEmail(user.getSlackEmail())
        .build();
  }

}
