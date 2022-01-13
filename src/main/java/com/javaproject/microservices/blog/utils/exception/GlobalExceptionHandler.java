package com.javaproject.microservices.blog.utils.exception;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ ResponseStatusException.class })
  protected ResponseEntity<ErrorResponse> handleDevGlobalException(
    ResponseStatusException ex,
    Locale locale
  ) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity<>(
      ErrorResponse
        .builder()
        .message(ex.getReason())
        .description(ex.getLocalizedMessage())
        .status(ex.getStatus().value())
        .build(),
      ex.getStatus()
    );
  }

  @ExceptionHandler({ Exception.class })
  protected ResponseEntity<ErrorResponse> handleGlobalException(
    Exception ex,
    Locale locale
  ) {
    logger.error(ex.getMessage(), ex);
    return new ResponseEntity<>(
      ErrorResponse
        .builder()
        .message(ex.getMessage())
        .description(ex.toString())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .build(),
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
