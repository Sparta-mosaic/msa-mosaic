package org.mosaic.order_service.libs.exception;

import java.util.HashMap;
import java.util.Map;
import org.mosaic.order_service.libs.util.ApiResponseUtils;
import org.mosaic.order_service.libs.util.MosaicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleNumberFormatException(Exception ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", "잘못된 입력 형식입니다: " + ex.getMessage());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<MosaicResponse<String>> handleCustomException(AuthException e) {
    return ApiResponseUtils.failed(e.getStatus(), e);
  }
}
