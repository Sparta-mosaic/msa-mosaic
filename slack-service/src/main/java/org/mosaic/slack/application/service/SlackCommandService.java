package org.mosaic.slack.application.service;


import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.LayoutBlock;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.mosaic.slack.application.dto.SlackChannelTemplate;
import org.mosaic.slack.application.dto.SlackDirectTemplate;
import org.mosaic.slack.domain.entity.SlackMessages;
import org.mosaic.slack.domain.repository.SlackMessagesRepository;
import org.mosaic.slack.libs.exception.CustomException;
import org.mosaic.slack.libs.exception.ExceptionStatus;
import org.mosaic.slack.presentations.dto.CreateMessageRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SlackCommandService {

  @Value(value = "${slack.bot-token}")
  private String token;
  @Value(value="${slack.channel.monitor}")
  private String channel;

  private final SlackMessagesRepository slackMessagesRepository;

  public void createAndSendMessage(CreateMessageRequestDto requestMessage){

    String slackId = getSlackIdByEmail(requestMessage.getDeliveryPersonEmail());

    List<LayoutBlock> buildDirectMessage = SlackDirectTemplate.of(requestMessage);
    List<LayoutBlock> buildChannelMessage = SlackChannelTemplate.of();

    MethodsClient methods = Slack.getInstance().methods(token);
    ChatPostMessageRequest request1 = ChatPostMessageRequest.builder()
        .channel(slackId).blocks(buildDirectMessage).build();
    ChatPostMessageRequest request2 = ChatPostMessageRequest.builder()
        .channel(channel).blocks(buildChannelMessage).build();

    SlackMessages slack = SlackMessages.create(
        slackId,
        requestMessage.getDeliveryPersonEmail(),
        buildDirectMessage.toString());
    slackMessagesRepository.save(slack);

    try {
      methods.chatPostMessage(request1);
      methods.chatPostMessage(request2);
    } catch (Exception e) {
      throw new CustomException(ExceptionStatus.MESSAGE_REQUEST_FAILED);
    }
  }



  public void deleteSlackMessage(String userUuid, Long messageId) {

    SlackMessages slackMessages = slackMessagesRepository.findById(messageId)
        .orElseThrow(() -> new CustomException(ExceptionStatus.SLACK_MESSAGE_NOT_FOUND));

    slackMessages.softDelete(userUuid);
  }



  public String getSlackIdByEmail(String email) {

    String slackUrl = "https://slack.com/api/users.lookupByEmail" + "?email=" + email;

    log.info("[service] >>>>>>>>>>>>>>>>>>>> slackId 요청 by email : {}", email);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.add("Content-type", "application/x-www-form-urlencoded");

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        slackUrl,
        HttpMethod.GET,
        requestEntity,
        String.class
    );
    JSONObject jsonObject;
    jsonObject = new JSONObject(responseEntity.getBody());
    JSONObject profile = jsonObject.getJSONObject("user");
    String id = (String)profile.get("id");
    log.info("[service] >>>>>>>>>>>>>>>>>>>> slackId : {}", id);

    return id;
  }

}
