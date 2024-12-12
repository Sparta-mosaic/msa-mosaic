package org.mosaic.auth.application.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.entity.user.UserRole;
import org.mosaic.auth.domain.entity.user.User;

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