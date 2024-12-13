package org.mosaic.auth.user.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.user.application.dto.UserPageResponse;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

  private final UserRepository userRepository;

  public UserResponse findUserById(Long userId) {

    // TODO: 요청자 본인의 정보만 확인 할 수 있도록 설정 필요 (MASTER 제외)

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    return UserResponse.of(user);
  }

  public UserPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    Page<User> userEntityPage = userRepository.findAll(booleanBuilder, pageable);
    return UserPageResponse.of(userEntityPage);

  }
}
