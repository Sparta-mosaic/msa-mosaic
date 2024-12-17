package org.mosaic.auth.application.dtos.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateUserDto {

    private String userUuid;
    private String slackEmail;

    public static UpdateUserDto create(String uuid, String slackEmail) {
        return UpdateUserDto.builder()
            .userUuid(uuid)
            .slackEmail(slackEmail)
            .build();
    }
}
