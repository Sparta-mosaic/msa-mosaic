package org.mosaic.auth.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.UserDto;
import org.mosaic.auth.application.dtos.UserPageResponse;
import org.mosaic.auth.application.dtos.UserResponse;
import org.mosaic.auth.domain.entity.user.User;
import org.mosaic.auth.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.mosaic.auth.domain.entity.user.QUser.user;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserResponse createUser(UserDto request) {

    User user = User.create(
        request.getUsername(),
        request.getPassword(),
        request.getRole(),
        request.getSlackEmail()
    );

    return UserResponse.of(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public UserResponse findUserById(Long userId) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    return UserResponse.of(user);
  }

  @Transactional(readOnly = true)
  public UserPageResponse findAllByQueryDslPaging(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    booleanBuilder.and(user.isPublic.eq(true));
    Page<User> userEntityPage = userRepository.findAll(booleanBuilder, pageable);
    return UserPageResponse.of(userEntityPage);

  }
}
