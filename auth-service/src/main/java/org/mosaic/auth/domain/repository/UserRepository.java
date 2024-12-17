package org.mosaic.auth.domain.repository;

import java.util.Optional;
import org.mosaic.auth.domain.model.user.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends QuerydslPredicateExecutor<User> {

  User save(User user);

  Optional<User> findById(Long userId);

  Optional<User> findByUsername(String username);

   Optional<User> findByUserUUID(String userUuid);

}
