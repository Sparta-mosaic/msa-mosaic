package org.mosaic.auth.presentations.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.application.dtos.UserDto;
import org.mosaic.auth.domain.entity.user.UserRole;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    private String username;
    private String password;
    private UserRole role;
    private String slackEmail;

    public UserDto toDTO() {
        return UserDto.create(username, password, role, slackEmail);
    }

}
