package org.mosaic.slack.domain.repository;

import java.util.Optional;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessagesRepository extends
    QuerydslPredicateExecutor<SlackMessages> {
  
  Optional<SlackMessages> findById(Long messageId);

  SlackMessages save(SlackMessages slack);
}
