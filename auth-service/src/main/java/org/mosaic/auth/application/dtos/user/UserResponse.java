package org.mosaic.auth.application.dtos.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.user.UserRole;
import org.mosaic.auth.domain.model.user.User;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserResponse {

  private Long userId;
  private String userUuid;
  private String username;
  private UserRole role;
  private String slackEmail;

  public static UserResponse of(User user){
    return UserResponse.builder()
        .userId(user.getUserId())
        .userUuid(user.getUserUUID())
        .username(user.getUsername())
        .role(user.getRole())
        .slackEmail(user.getSlackEmail())
        .build();
  }
}
