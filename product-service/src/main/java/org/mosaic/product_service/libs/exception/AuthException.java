package org.mosaic.product_service.libs.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthException extends RuntimeException{
	private final HttpStatus status;
	private final String message;
}
