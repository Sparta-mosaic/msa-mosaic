package org.mosaic.auth.libs.security.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.domain.model.user.UserRole;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class AuthenticatedUserDto {

  private Long userId;
  private String userUUID;
  private String username;
  private String password;
  private UserRole role;
  private Boolean isActivate;

  public static AuthenticatedUserDto createAuthenticatedUserDto(String userUUID, UserRole role, Boolean isActivate) {
    return AuthenticatedUserDto.builder()
        .userUUID(userUUID)
        .role(role)
        .isActivate(isActivate)
        .build();
  }
}
