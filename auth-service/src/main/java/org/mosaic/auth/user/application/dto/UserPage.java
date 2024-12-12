package org.mosaic.auth.user.application.dto;

import lombok.Getter;
import lombok.ToString;
import org.mosaic.auth.user.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class UserPage extends PagedModel<User> {

  public UserPage(Page<User> userPage) {
    super(
        new PageImpl<>(
          userPage.getContent(),
          userPage.getPageable(),
          userPage.getTotalElements()
        )
    );
  }
}
