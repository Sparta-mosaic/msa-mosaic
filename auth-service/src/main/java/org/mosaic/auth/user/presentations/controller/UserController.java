package org.mosaic.auth.user.presentations.controller;

import static org.mosaic.auth.libs.util.ApiResponseUtil.success;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.application.service.UserCommandService;
import org.mosaic.auth.user.application.service.UserQueryService;
import org.mosaic.auth.user.presentations.dto.SignUpCompanyRequest;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResult<UserResponse>> getUser(
        @PathVariable String userId,
        @AuthenticationPrincipal CustomUserDetails userDetails) {

        return new ResponseEntity<>(success(
            userQueryService.findUserBySelf(userId, userDetails)),
            HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<ApiResult<String>> signIn(
        @RequestBody @Valid SignUpCompanyRequest request) {

        userCommandService.createUser(request.toDto(passwordEncoder));

        return new ResponseEntity<>(success(
            "SignUp Successful !!"),
            HttpStatus.OK);
    }
}
