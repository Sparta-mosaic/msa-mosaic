package org.mosaic.auth.user.presentations;

import static org.mosaic.auth.libs.ApiResponseUtil.success;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.user.application.dto.UserPageResponse;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.application.service.UserService;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.libs.ApiResponseUtil.ApiResult;
import org.mosaic.auth.user.presentations.dto.CreateUserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResult<UserResponse>> getUser(
        @PathVariable Long userId) {

        return new ResponseEntity<>(success(
            userService.findUserById(userId)),
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserPageResponse> findPageByQuerydsl(
        @QuerydslPredicate(root = User.class) Predicate predicate,
        @PageableDefault(sort = "userId", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(userService.findAllByQueryDslPaging(predicate, pageable));
    }

    @PostMapping
    public ResponseEntity<ApiResult<UserResponse>> createUser(
        @RequestBody CreateUserRequest request) {

        return new ResponseEntity<>(success(
            userService.createUser(request.toDTO())),
            HttpStatus.CREATED);
    }

}
