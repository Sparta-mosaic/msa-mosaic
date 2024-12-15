package org.mosaic.slack.application.service;

import static org.mosaic.slack.application.dto.SlackMessageBuilderDto.buildMessage;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.LayoutBlock;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mosaic.slack.application.dto.SlackMessageBuilderDto;
import org.mosaic.slack.application.dto.UserFeignResponse;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.mosaic.slack.domain.repository.SlackMessagesRepository;
import org.mosaic.slack.infrastructure.UserClient;
import org.mosaic.slack.libs.exception.CustomException;
import org.mosaic.slack.libs.exception.ExceptionStatus;
import org.mosaic.slack.presentations.dto.SendMessageRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackService {

  @Value(value = "${slack.bot-token}")
  private String token;

  @Value(value="${slack.channel.monitor}")
  private String channel;

  private final SlackMessagesRepository slackMessagesRepository;
  private final UserClient userClient;

  public UserFeignResponse getUser(Long userId) {
    return userClient.getUser(userId);
  }

  @Transactional
  public void sendMessageToSlack(SendMessageRequestDto requestText){

    UserFeignResponse customer = getUser(requestText.getCustomerId());
    UserFeignResponse deliveryMan = getUser(requestText.getDeliveryManagerId());

    List<LayoutBlock> buildMessage = buildMessage(
        SlackMessageBuilderDto.create(
        "OrderNo",
        customer.getUsername() + " / " + customer.getSlackEmail(),
        "ProductInfo",
        "departure",
        "stopover",
        "arrival",
            deliveryMan.getUsername() + " / " + deliveryMan.getSlackEmail(),
        "\"Family in town, going camping!\"",
        "12월 10일 오전 9시")
        );


    MethodsClient methods = Slack.getInstance().methods(token);
    ChatPostMessageRequest request = ChatPostMessageRequest.builder()
        .channel(channel).blocks(buildMessage).build();

    slackMessagesRepository.save(SlackMessages.create(buildMessage.toString()));

    try {
      methods.chatPostMessage(request);
    } catch (Exception e) {
      throw new CustomException(ExceptionStatus.MESSAGE_REQUEST_FAILED);
    }
  }

}
