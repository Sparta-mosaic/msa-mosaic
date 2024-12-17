package org.mosaic.auth.presentation.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.application.dtos.user.UpdateUserDto;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    @NotBlank
    private String userUuid;
    @NotBlank
    private String slackEmail;

    public UpdateUserDto toDTO(){
        return UpdateUserDto.create(userUuid, slackEmail);
    }
}
