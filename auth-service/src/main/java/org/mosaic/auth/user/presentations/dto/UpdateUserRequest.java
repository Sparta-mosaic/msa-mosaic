package org.mosaic.auth.user.presentations.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.user.application.dto.UserDto;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    @NotBlank
    private String userUuid;
    @NotBlank
    private String username;
    @NotBlank
    private String slackEmail;

    public UserDto toDTO(){
        return UserDto.create(
            userUuid, username, null, null, slackEmail);
    }

}
