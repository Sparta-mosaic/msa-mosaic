package org.mosaic.auth.domain.repository;

import java.util.Optional;
import org.mosaic.auth.domain.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  User save(User user);

  Optional<User> findById(Long userId);

}
