package com.javaproject.microservices.blog.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

  private long id;

  private String title;

  private String content;

  private UserDto userDto;

  private Timestamp createdAt;

  private Timestamp updatedAt;
}
