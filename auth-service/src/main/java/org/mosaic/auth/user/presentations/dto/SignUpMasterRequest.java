package org.mosaic.auth.user.presentations.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class SignUpMasterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String slackEmail;

    public UserDto toDTO(PasswordEncoder passwordEncoder) {
        return UserDto.create(username, passwordEncoder.encode(password), UserRole.MASTER, slackEmail);
    }
}
