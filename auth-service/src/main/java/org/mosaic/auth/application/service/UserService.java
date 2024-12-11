package org.mosaic.auth.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.UserDto;
import org.mosaic.auth.application.dtos.UserResponse;
import org.mosaic.auth.domain.entity.user.User;
import org.mosaic.auth.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  // update

  // delete
}
