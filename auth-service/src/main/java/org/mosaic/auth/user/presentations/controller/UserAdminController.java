package org.mosaic.auth.user.presentations.controller;

import static org.mosaic.auth.libs.util.ApiResponseUtil.success;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.util.ApiResponseUtil.ApiResult;
import org.mosaic.auth.user.application.dto.UserPageResponse;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.application.service.UserCommandService;
import org.mosaic.auth.user.application.service.UserQueryService;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.presentations.dto.SignUpMasterRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admin/auth")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<ApiResult<UserPageResponse>> findPageByQuerydsl(
        @QuerydslPredicate(root = User.class) Predicate predicate,
        @PageableDefault(sort = "userId", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return new ResponseEntity<>(success(
            userQueryService.findAllByQueryDslPaging(predicate, pageable)),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<UserResponse>> createMasterUser(
        @RequestBody SignUpMasterRequest request) {

        return new ResponseEntity<>(success(
            userCommandService.createUser(request.toDTO(passwordEncoder))),
            HttpStatus.CREATED);
    }

}
