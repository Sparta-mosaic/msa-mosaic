package org.mosaic.ai.domain.repository;

import org.mosaic.ai.domain.entity.AIInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIPromptLogRepository extends JpaRepository<AIInteractionLog, Long> {

}
