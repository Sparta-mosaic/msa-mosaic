package org.mosaic.auth.user.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.domain.entity.user.UserRole;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpUserDto {

  private String username;
  private String password;
  private UserRole role;
  private String slackEmail;

  public static SignUpUserDto create(String username, String password, UserRole role, String slackEmail) {
    return SignUpUserDto.builder()
        .username(username)
        .password(password)
        .role(role)
        .slackEmail(slackEmail)
        .build();
  }

}
