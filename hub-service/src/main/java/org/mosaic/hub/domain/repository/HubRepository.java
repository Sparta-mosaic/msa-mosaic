package org.mosaic.hub.domain.repository;


import java.util.Optional;
import org.mosaic.hub.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubRepository extends JpaRepository<Hub, Long> {

  Optional<Hub> findByUuid(String uuid);
}
