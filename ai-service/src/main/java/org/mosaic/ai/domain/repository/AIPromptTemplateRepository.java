package org.mosaic.ai.domain.repository;

import java.util.Optional;
import org.mosaic.ai.domain.entity.AIPromptTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIPromptTemplateRepository extends JpaRepository<AIPromptTemplate, Long> {

  Optional<AIPromptTemplate> findFirstByOrderByCreatedAtDesc();
}
