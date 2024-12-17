package org.mosaic.auth.libs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {
  // User
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),
  AUTHENTICATION_INVALID_USER(HttpStatus.UNAUTHORIZED, "잘못된 로그인 정보입니다."),
  INVALID_USERNAME(HttpStatus.UNAUTHORIZED, "중복된 회원입니다."),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 접근입니다."),


  // Company
  COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "업체가 존재하지 않습니다."),
  INVALID_HUB_ID(HttpStatus.BAD_REQUEST, "허브가 존재하지 않습니다."),

  // Delivery Driver
  DELIVERY_DRIVER_NOT_FOUND(HttpStatus.NOT_FOUND, "배송 담당자가 존재하지 않습니다.");

  private final int status;
  private final String message;
  private final String err;

  ExceptionStatus(HttpStatus httpStatus, String message) {
    this.status = httpStatus.value();
    this.message = message;
    this.err = httpStatus.getReasonPhrase();
  }

}
