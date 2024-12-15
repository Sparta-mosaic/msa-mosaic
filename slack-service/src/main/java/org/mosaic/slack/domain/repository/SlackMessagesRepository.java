package org.mosaic.slack.domain.repository;

import org.mosaic.slack.domain.entity.SlackMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessagesRepository extends
    JpaRepository<SlackMessages, Long> {

}
