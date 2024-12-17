package org.mosaic.auth.application.service.user;

import lombok.RequiredArgsConstructor;
import org.mosaic.auth.application.dtos.user.SignUpUserDto;
import org.mosaic.auth.libs.exception.CustomException;
import org.mosaic.auth.libs.exception.ExceptionStatus;
import org.mosaic.auth.libs.security.entity.CustomUserDetails;
import org.mosaic.auth.application.dtos.user.UpdateUserDto;
import org.mosaic.auth.application.dtos.user.UserResponse;
import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.domain.model.user.UserRole;
import org.mosaic.auth.domain.repository.UserRepository;
import org.mosaic.auth.presentation.dtos.user.UpdateUserRoleRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {

  private final UserRepository userRepository;

  public UserResponse createUser(SignUpUserDto request) {
    if(userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new CustomException(ExceptionStatus.INVALID_USERNAME);
    }

    User user = User.create(
        request.getUsername(),
        request.getPassword(),
        request.getRole(),
        request.getSlackEmail()
    );

    user.createdBy(user.getUserUUID());
    return UserResponse.of(userRepository.save(user));
  }

  public void updateUserSlack(UpdateUserDto request, CustomUserDetails userDetails) {
    User user = userRepository.findByUserUUID(request.getUserUuid())
        .orElseThrow(() -> new CustomException(ExceptionStatus.USER_NOT_FOUND));
    user.update(request.getSlackEmail());
    user.updatedBy(userDetails.getUserUuid());
  }

  public UserResponse updateUserRole(UpdateUserRoleRequest request, CustomUserDetails userDetails) {
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
}
