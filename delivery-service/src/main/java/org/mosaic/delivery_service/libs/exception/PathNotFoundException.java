package org.mosaic.delivery_service.libs.exception;

public class PathNotFoundException extends RuntimeException {
  public PathNotFoundException(String message) {
    super(message);
  }
}
