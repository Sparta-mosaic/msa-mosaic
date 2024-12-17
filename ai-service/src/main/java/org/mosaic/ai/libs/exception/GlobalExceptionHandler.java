package org.mosaic.ai.libs.exception;

import static org.mosaic.ai.libs.util.ApiResponseUtil.error;

import org.mosaic.ai.libs.util.ApiResponseUtil.ApiError;
import org.mosaic.ai.libs.util.ApiResponseUtil.ApiResult;
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
