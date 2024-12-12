package org.mosaic.hub.domain.repository;


import org.mosaic.hub.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubRepository extends JpaRepository<Hub, Long> {
}
