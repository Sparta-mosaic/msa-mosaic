package org.mosaic.slack.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.mosaic.slack.application.dto.SlackMessagePageResponse;
import org.mosaic.slack.application.dto.SlackMessageResponse;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.mosaic.slack.domain.repository.SlackMessagesRepository;
import org.mosaic.slack.libs.exception.CustomException;
import org.mosaic.slack.libs.exception.ExceptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SlackQueryService {

  private final SlackMessagesRepository slackMessagesRepository;

  public String getMessageById(Long messageId) {

    SlackMessages slackMessages = slackMessagesRepository.findById(messageId)
        .orElseThrow(() -> new CustomException(ExceptionStatus.SLACK_MESSAGE_NOT_FOUND));

    return slackMessages.getText();
  }

  public SlackMessagePageResponse findAllMessages(Predicate predicate, Pageable pageable) {

    BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
    Page<SlackMessages> messagePage = slackMessagesRepository.findAll(booleanBuilder, pageable);

    return SlackMessagePageResponse.of(messagePage.map(SlackMessageResponse::of));
  }
}
