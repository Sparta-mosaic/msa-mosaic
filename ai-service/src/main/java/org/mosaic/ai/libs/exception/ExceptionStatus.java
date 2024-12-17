package org.mosaic.ai.libs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

  AI_PROMPT_TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하는 탬플릿이 없습니다."),

  BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
  NOT_FOUND(HttpStatus.NOT_FOUND, "응답을 찾을 수 없습니다."),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 요청입니다.");


  private final int status;
  private final String message;
  private final String err;

  ExceptionStatus(HttpStatus httpStatus, String message) {
    this.status = httpStatus.value();
    this.message = message;
    this.err = httpStatus.getReasonPhrase();
  }

}
