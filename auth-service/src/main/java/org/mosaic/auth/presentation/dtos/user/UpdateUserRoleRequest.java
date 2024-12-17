package org.mosaic.auth.presentation.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateUserRoleRequest {

    @NotBlank
    private String userUuid;
    @NotBlank
    private String role;
}
