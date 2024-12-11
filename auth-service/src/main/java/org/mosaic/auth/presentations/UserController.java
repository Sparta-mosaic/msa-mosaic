package org.mosaic.auth.presentations;

import static org.mosaic.auth.libs.ApiResponseUtil.success;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.CompanyResponse;
import org.mosaic.auth.application.dtos.UserResponse;
import org.mosaic.auth.application.service.UserService;
import org.mosaic.auth.libs.ApiResponseUtil.ApiResult;
import org.mosaic.auth.presentations.dtos.CreateCompanyRequest;
import org.mosaic.auth.presentations.dtos.CreateUserRequest;
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
        @PathVariable String userId) {

        return new ResponseEntity<>(success(
            userService.findUserById(userId)),
            HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResult<CompanyResponse>> getCompany(
        @PathVariable String companyId) {

        return new ResponseEntity<>(success(
            userService.findCompanyById(companyId)),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResult<UserResponse>> createUser(
        @RequestBody CreateUserRequest request) {

      return new ResponseEntity<>(success(
          userService.createUser(request.toDTO())),
          HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ApiResult<CompanyResponse>> createCompany(
        @RequestBody CreateCompanyRequest request){

        return new ResponseEntity<>(success(
            userService.createCompany(request.toDTO())),
            HttpStatus.CREATED);
    }

}
