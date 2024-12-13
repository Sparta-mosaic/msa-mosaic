package org.mosaic.auth.user.presentations.controller;

import static org.mosaic.auth.libs.util.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.application.service.UserCommandService;
import org.mosaic.auth.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.auth.user.application.service.UserQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResult<UserResponse>> getUser(
        @PathVariable Long userId) {

        return new ResponseEntity<>(success(
            userQueryService.findUserById(userId)),
            HttpStatus.OK);
    }

}
