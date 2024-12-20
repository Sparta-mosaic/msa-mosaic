package org.mosaic.slack.infrastructure;

import static org.mosaic.slack.libs.constant.HttpHeaderConstants.HEADER_USER_ID;

import org.mosaic.slack.libs.util.ApiResponseUtils.CommonResponse;
import org.mosaic.slack.presentations.dto.PromptRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ai-service")
public interface AIClient {

  @PostMapping("/api/v1/internal/ai/createResponse")
  ResponseEntity<CommonResponse<String>> createResponse(
      @RequestHeader(HEADER_USER_ID) String userUuid,
      @RequestBody PromptRequest prompt
  );
}
