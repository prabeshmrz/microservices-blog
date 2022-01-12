package com.javaproject.microservices.blog.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * DevGlobalException
 */
public class DevGlobalException extends ResponseStatusException {

  public DevGlobalException() {
    super(HttpStatus.BAD_REQUEST, "Something Went Wrong!");
  }

  public DevGlobalException(HttpStatus status, String reason) {
    super(status, reason);
  }
}
