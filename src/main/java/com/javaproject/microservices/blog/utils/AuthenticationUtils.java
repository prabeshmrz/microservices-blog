package com.javaproject.microservices.blog.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

public class AuthenticationUtils {

  public static String getUserId(boolean required) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    String userId = null;
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      userId = authentication.getName();
    } else if (required) throw new ResponseStatusException(
      HttpStatus.UNAUTHORIZED,
      "USER NOT FOUND"
    );
    return userId;
  }
}
