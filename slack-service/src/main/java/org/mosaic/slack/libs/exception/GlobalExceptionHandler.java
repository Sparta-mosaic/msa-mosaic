package org.mosaic.slack.libs.exception;

import static org.mosaic.slack.libs.util.ApiResponseUtil.error;

import org.mosaic.slack.libs.util.ApiResponseUtil.ApiError;
import org.mosaic.slack.libs.util.ApiResponseUtil.ApiResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResult<ApiError>> handleCustomException(CustomException e) {
		return new ResponseEntity<>(error(e.getMessage()), new HttpHeaders(), e.getStatus());
	}
}
