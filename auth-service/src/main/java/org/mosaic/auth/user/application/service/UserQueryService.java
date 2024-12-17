package org.mosaic.auth.user.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.user.application.dto.UserFeignResponse;
import org.mosaic.auth.user.application.dto.UserPageResponse;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    Page<User> userEntityPage = userRepository.findAll(booleanBuilder, pageable);
    return UserPageResponse.of(userEntityPage);

  }

  public UserFeignResponse getFeignUserResponse(String  userUuid) {

    User user = userRepository.findByUserUUID(userUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    return UserFeignResponse.of(user);
  }

  public User getAuthenticateUser(String username, String password) {

    User generalUser = userRepository.findByUsername(username)
        .orElseThrow(() -> new CustomException(ExceptionStatus.AUTHENTICATION_INVALID_USER));

    if(!passwordEncoder.matches(password, generalUser.getPassword())) {
      throw new CustomException(ExceptionStatus.AUTHENTICATION_INVALID_USER);
    }

    return generalUser;
  }

  public User getUserByUsername(String username) {

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));
  }

  public UserResponse findUserBySelf(String userUuid, CustomUserDetails userDetails) {

    User user = userRepository.findByUserUUID(userUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    if(!userDetails.getUserRole().equals(UserRole.MASTER) &&
        !user.getUserUUID().equals(userDetails.getUserUuid())) {
      throw new CustomException(ExceptionStatus.AUTHENTICATION_INVALID_USER);
    }

    return UserResponse.of(user);
  }

}
