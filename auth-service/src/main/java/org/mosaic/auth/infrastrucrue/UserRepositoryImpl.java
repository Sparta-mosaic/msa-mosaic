package org.mosaic.auth.infrastrucrue;

import org.mosaic.auth.domain.entity.user.User;
import org.mosaic.auth.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, Long>,
    UserRepository {

}
