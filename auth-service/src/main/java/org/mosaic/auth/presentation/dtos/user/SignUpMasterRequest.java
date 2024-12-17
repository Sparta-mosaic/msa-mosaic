package org.mosaic.auth.presentation.dtos.user;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.application.dtos.user.SignUpUserDto;
import org.mosaic.auth.domain.model.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class SignUpMasterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String slackEmail;

    public SignUpUserDto toDTO(PasswordEncoder passwordEncoder) {
        return SignUpUserDto.create(
            username, passwordEncoder.encode(password),
            UserRole.ROLE_MASTER, slackEmail);
    }
}
