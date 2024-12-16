package org.mosaic.slack.domain.repository;

import org.mosaic.slack.domain.entity.SlackMessages;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessagesRepository {

  SlackMessages save(SlackMessages slackMessages);
}
