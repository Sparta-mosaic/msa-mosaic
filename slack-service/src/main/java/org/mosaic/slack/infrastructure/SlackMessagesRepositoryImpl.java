package org.mosaic.slack.infrastructure;

import org.mosaic.slack.domain.entity.SlackMessages;
import org.mosaic.slack.domain.repository.SlackMessagesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlackMessagesRepositoryImpl extends
    JpaRepository<SlackMessages, Long>, SlackMessagesRepository {

}
