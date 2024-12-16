package org.mosaic.auth.user.presentations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mosaic.auth.user.application.dto.UserFeignResponse;
import org.mosaic.auth.user.application.service.UserQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/feign/auth")
@RequiredArgsConstructor
@Slf4j
public class UserFeignController {

    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public UserFeignResponse getUser(
        @PathVariable Long userId) {
        log.info("Get User By FeignClient Success!!");
        return userQueryService.getFeignUserResponse(userId);

    }

}
