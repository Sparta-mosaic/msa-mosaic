package org.mosaic.auth.application.service.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.application.dtos.user.UserFeignResponse;
import org.mosaic.auth.application.dtos.user.UserPageResponse;
import org.mosaic.auth.application.dtos.user.UserResponse;
import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.domain.model.user.UserRole;
import org.mosaic.auth.domain.repository.UserRepository;
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

  public UserResponse getUserByUuid(String userUuid, CustomUserDetails userDetails) {

    User user = userRepository.findByUserUUID(userUuid)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    if(userDetails.getUserRole() != UserRole.ROLE_MASTER
        && !userUuid.equals(userDetails.getUserUuid())) {
      throw new CustomException(ExceptionStatus.AUTHENTICATION_INVALID_USER);
    }

    return UserResponse.of(user);
  }
}
