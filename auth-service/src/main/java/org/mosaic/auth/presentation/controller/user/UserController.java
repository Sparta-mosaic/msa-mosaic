package org.mosaic.auth.presentation.controller.user;


import static org.mosaic.auth.libs.util.ApiResponseUtils.created;
import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.user.UserResponse;
import org.mosaic.auth.application.service.user.UserCommandService;
import org.mosaic.auth.application.service.user.UserQueryService;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.auth.presentation.dtos.user.SignUpUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{userUuid}")
    public ResponseEntity<CommonResponse<UserResponse>> getUser(
        @PathVariable String userUuid,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ok(userQueryService.getUserByUuid(userUuid, userDetails));
    }

    @PostMapping("/signUp")
    public ResponseEntity<CommonResponse<String>> signUp(
        @RequestBody @Valid SignUpUserRequest request) {
        userCommandService.createUser(request.toDto(passwordEncoder));
        return created("SignUp Successful !!");
    }
}
