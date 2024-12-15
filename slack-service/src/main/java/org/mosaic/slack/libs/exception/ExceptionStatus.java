package org.mosaic.slack.libs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

  MESSAGE_REQUEST_FAILED(HttpStatus.BAD_REQUEST, "메시지 전송에 실패하였습니다.");

  private final int status;
  private final String message;
  private final String err;

  ExceptionStatus(HttpStatus httpStatus, String message) {
    this.status = httpStatus.value();
    this.message = message;
    this.err = httpStatus.getReasonPhrase();
  }

}
