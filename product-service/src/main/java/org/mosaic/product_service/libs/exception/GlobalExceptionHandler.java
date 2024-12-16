package org.mosaic.product_service.libs.exception;

import java.util.HashMap;
import java.util.Map;
import org.mosaic.product_service.libs.util.ApiResponseUtils;
import org.mosaic.product_service.libs.util.MosaicResponse;
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
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Map<String, String>> handleNumberFormatException(Exception ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", "잘못된 접근입니다 : " + ex.getMessage());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(AuthException.class)
  public ResponseEntity<MosaicResponse<String>> handleAuthException(AuthException e) {
    return ApiResponseUtils.failed(e.getStatus(), e);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Map<String, String>> handleProductNotFoundException(
      ProductNotFoundException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(InsufficientStockException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleInsufficientStockException(
      InsufficientStockException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", "재고 부족: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
