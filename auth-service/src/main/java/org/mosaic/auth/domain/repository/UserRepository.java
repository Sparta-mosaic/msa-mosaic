package org.mosaic.auth.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.mosaic.auth.domain.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  User save(User user);

  Optional<User> findById(UUID userId);

  Optional<User> findByUsername(String username);
}
