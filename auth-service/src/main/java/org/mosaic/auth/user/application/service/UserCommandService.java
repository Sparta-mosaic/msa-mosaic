package org.mosaic.auth.user.application.service;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {

  private final UserRepository userRepository;

  public UserResponse createUser(UserDto request) {

    User user = User.create(
        request.getUsername(),
        request.getPassword(),
        request.getRole(),
        request.getSlackEmail()
    );

    return UserResponse.of(userRepository.save(user));
  }

}
