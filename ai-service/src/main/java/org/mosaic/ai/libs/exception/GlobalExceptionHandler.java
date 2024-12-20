package org.mosaic.ai.libs.exception;

import org.mosaic.ai.libs.util.ApiResponseUtils;
import org.mosaic.ai.libs.util.ApiResponseUtils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CommonResponse<String>> handleCustomException(ExceptionStatus e) {
		return ApiResponseUtils.failed(e);
	}
}
