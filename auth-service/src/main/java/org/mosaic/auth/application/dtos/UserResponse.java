package org.mosaic.auth.application.dtos;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.entity.user.UserRole;
import org.mosaic.auth.domain.entity.user.User;

@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserResponse {

  private UUID userId;
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
