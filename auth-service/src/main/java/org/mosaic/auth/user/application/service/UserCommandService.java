package org.mosaic.auth.user.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.user.application.dto.UserDto;
import org.mosaic.auth.user.application.dto.UserResponse;
import org.mosaic.auth.user.domain.entity.user.User;
import org.mosaic.auth.user.domain.entity.user.UserRole;
import org.mosaic.auth.user.domain.repository.UserRepository;
import org.mosaic.auth.user.presentations.dto.UpdateUserRoleRequest;
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

    user.createdBy(user.getUserUUID());

    return UserResponse.of(userRepository.save(user));
  }


  public Object updateUserRole(UpdateUserRoleRequest request, CustomUserDetails userDetails) {

    User user = userRepository.findByUserUUID(request.getUserUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    user.updateUserRole(UserRole.valueOf(request.getRole()));
    user.updatedBy(userDetails.getUserUuid());

    return UserResponse.of(user);
  }

  public void delete(Long userId, CustomUserDetails userDetails) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    user.delete(userDetails.getUserUuid());

  }

  public void updateUser(UserDto request, CustomUserDetails userDetails) {

    User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));

    user.update(request.getSlackEmail());
    user.updatedBy(userDetails.getUserUuid());
  }
}
