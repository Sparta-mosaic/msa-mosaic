package org.mosaic.auth.presentation.controller.user;

import static org.mosaic.auth.libs.util.ApiResponseUtils.created;
import static org.mosaic.auth.libs.util.ApiResponseUtils.noContent;
import static org.mosaic.auth.libs.util.ApiResponseUtils.ok;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.user.UserPageResponse;
import org.mosaic.auth.application.dtos.user.UserResponse;
import org.mosaic.auth.application.service.user.UserCommandService;
import org.mosaic.auth.application.service.user.UserQueryService;
import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.auth.presentation.dtos.user.SignUpMasterRequest;
import org.mosaic.auth.presentation.dtos.user.UpdateUserRequest;
import org.mosaic.auth.presentation.dtos.user.UpdateUserRoleRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/admin/auth")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<CommonResponse<UserPageResponse>> findPageByQuerydsl(
        @QuerydslPredicate(root = User.class) Predicate predicate,
        @PageableDefault(sort = "userId", direction = Sort.Direction.DESC) Pageable pageable) {
        return ok(userQueryService.findAllByQueryDslPaging(predicate, pageable));
    }

    @PostMapping("/signUp")
    public ResponseEntity<CommonResponse<UserResponse>> createMasterUser(
        @RequestBody SignUpMasterRequest request) {
        return created(userCommandService.createUser(request.toDTO(passwordEncoder)));
    }

    @PutMapping
    public ResponseEntity<CommonResponse<String>> updateUserSlack(
        @RequestBody UpdateUserRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        userCommandService.updateUserSlack(request.toDTO(), userDetails);
        return ok("User Info Updated !!");
    }

    @PatchMapping
    public ResponseEntity<CommonResponse<UserResponse>> updateUserRole(
        @RequestBody UpdateUserRoleRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ok(userCommandService.updateUserRole(request, userDetails));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponse<Void>> deleteCompany(
        @PathVariable Long userId,
        @AuthenticationPrincipal CustomUserDetails userDetails){
        userCommandService.delete(userId, userDetails);
        return noContent();
    }
}
