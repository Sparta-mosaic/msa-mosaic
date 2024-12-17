package org.mosaic.auth.infrastructure;

import org.mosaic.auth.domain.model.user.User;
import org.mosaic.auth.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, Long>,
    UserRepository
{

}
