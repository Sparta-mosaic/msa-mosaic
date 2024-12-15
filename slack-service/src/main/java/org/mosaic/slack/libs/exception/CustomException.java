package org.mosaic.slack.libs.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final ExceptionStatus exceptionStatus;

  public CustomException(ExceptionStatus exceptionStatus) {
    this.exceptionStatus = exceptionStatus;
  }

  @Override
  public String getMessage() {
    return exceptionStatus.getMessage();
  }

  public int getStatus() {
    return exceptionStatus.getStatus();
  }
}
