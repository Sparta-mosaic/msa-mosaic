package org.mosaic.auth.libs.security.entity;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mosaic.auth.domain.model.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

  private final AuthenticatedUserDto authenticatedUserDto;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(
        (GrantedAuthority) ()
            -> authenticatedUserDto.getRole().toString()
    );
  }

  @Override
  public String getPassword() {
    return authenticatedUserDto.getPassword();
  }

  @Override
  public String getUsername() {
    return authenticatedUserDto.getUserUUID();
  }

  public String getUserUuid() {
    return authenticatedUserDto.getUserUUID();
  }


  public UserRole getUserRole(){
    return authenticatedUserDto.getRole();
  }

  @Override
  public boolean isAccountNonExpired() {
    return authenticatedUserDto.getIsActivate();
  }

  @Override
  public boolean isAccountNonLocked() {
    return authenticatedUserDto.getIsActivate();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return authenticatedUserDto.getIsActivate();
  }

  @Override
  public boolean isEnabled() {
    return authenticatedUserDto.getIsActivate();
  }
}
