package org.mosaic.auth.user.presentations.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.domain.entity.user.UserRole;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateUserRequest {

    private String username;
    private String password;
    private UserRole role;
    private String slackEmail;

    public UserDto toDTO() {
        return UserDto.create(username, password, role, slackEmail);
    }

}
