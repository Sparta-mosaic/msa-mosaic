package org.mosaic.auth.presentation.controller.user;

import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.application.dtos.user.ClientUserResponse;
import org.mosaic.auth.application.service.user.UserQueryService;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientUserController {

    private final UserQueryService userQueryService;

    @GetMapping("/api/v1/internal/auth/{userUuid}")
    public ResponseEntity<CommonResponse<ClientUserResponse>> getUser(
        @PathVariable String userUuid) {
        log.info("[ClientUserController] Get User about : {}", userUuid);
        return ok(userQueryService
            .getClientUserResponse(userUuid));
    }
}
