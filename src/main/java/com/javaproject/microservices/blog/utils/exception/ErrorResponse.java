package com.javaproject.microservices.blog.utils.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private String message;
  private String description;
  private int status;
}
