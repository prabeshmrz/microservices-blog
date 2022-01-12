package com.javaproject.microservices.blog.utils.exception;

import org.springframework.http.HttpStatus;

/**
 * ResourceNotFoundException
 */
public class ResourceNotFoundException extends DevGlobalException {

  public ResourceNotFoundException() {
    super(
      HttpStatus.NOT_FOUND,
      "Resource You Are Trying To Access Is Unavailable!!"
    );
  }

  public ResourceNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
