package org.mosaic.auth.user.presentations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SignUpCompanyRequest {


  @NotBlank(message = "Username is required")
  @Size(min = 4, max = 10,
      message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 합니다.")
  @Pattern(regexp = "^[a-z0-9]+$",
      message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 합니다.")
  private String username;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 15,
      message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자가 포함되어야 합니다.")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
      message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자가 포함되어야 합니다.")
  private String password;

  private String slackEmail;

  public UserDto toDto(PasswordEncoder passwordEncoder) {
    return UserDto.create(null,
        username, passwordEncoder.encode(password),
        UserRole.COMPANY, slackEmail);

  }
}
