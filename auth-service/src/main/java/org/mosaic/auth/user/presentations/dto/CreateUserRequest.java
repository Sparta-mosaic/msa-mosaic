package org.mosaic.auth.user.presentations.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.domain.entity.user.UserRole;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private UserRole role;
    @NotBlank
    private String slackEmail;

    public UserDto toDTO() {
        return UserDto.create(username, password, role, slackEmail);
    }
}
