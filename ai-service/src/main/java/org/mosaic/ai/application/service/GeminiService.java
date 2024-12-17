package org.mosaic.ai.application.service;


import lombok.RequiredArgsConstructor;
import org.mosaic.ai.application.dtos.ChatResponse;
import org.mosaic.ai.domain.entity.AIInteractionLog;
import org.mosaic.ai.domain.entity.AIPromptTemplate;
import org.mosaic.ai.domain.repository.AIPromptLogRepository;
import org.mosaic.ai.domain.repository.AIPromptTemplateRepository;
import org.mosaic.ai.libs.exception.CustomException;
import org.mosaic.ai.libs.exception.ExceptionStatus;
import org.mosaic.ai.presentation.dtos.ChatRequest;
import org.mosaic.ai.presentation.dtos.RequestPrompt;
import org.mosaic.ai.presentation.dtos.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class GeminiService {

  private final AIPromptLogRepository logRepository;
  private final AIPromptTemplateRepository templateRepository;

  @Qualifier("geminiRestTemplate")
  @Autowired
  private RestTemplate restTemplate;

  @Value("${gemini.api.url}")
  private String apiUrl;

  @Value("${gemini.api.key}")
  private String geminiApiKey;

  @Value("${gemini.api.name}")
  private String geminiModel;

  public String getContents(RequestPrompt prompt, String userUuid) {

    AIPromptTemplate template = templateRepository
        .findFirstByOrderByCreatedAtDesc()
        .orElseThrow(() -> new CustomException(
            ExceptionStatus.AI_PROMPT_TEMPLATE_NOT_FOUND));

    String requestUrl = apiUrl + geminiApiKey;
    String requestPrompt = template.getTemplate() + prompt.toString();

    ChatRequest request = new ChatRequest(requestPrompt);
    ChatResponse response = restTemplate.postForObject(requestUrl, request, ChatResponse.class);

    String responseMessage = response.getCandidates().get(0).getContent().getParts().get(0).getText().toString();

    AIInteractionLog interactionLog = AIInteractionLog.create(requestPrompt,responseMessage, geminiModel);
    interactionLog.createdBy(userUuid);
    logRepository.save(interactionLog);

    return responseMessage;
  }

  public void registPromptTemplate(RequestTemplate template, String userUuid) {

    AIPromptTemplate newTemplate =  AIPromptTemplate.create(template.getTemplate());
    newTemplate.createdBy(userUuid);
    templateRepository.save(newTemplate);

  }
}
