package org.mosaic.auth.user.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
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

    if(userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new CustomException(ExceptionStatus.INVALID_USERNAME);
    }

    String uuid = UUID.randomUUID().toString();

    User user = User.create(
        uuid,
        request.getUsername(),
        request.getPassword(),
        request.getRole(),
        request.getSlackEmail()
    );

    user.createby(user.getUserUUID());

    return UserResponse.of(userRepository.save(user));
  }


}
