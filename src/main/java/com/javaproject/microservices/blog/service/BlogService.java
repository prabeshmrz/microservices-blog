package com.javaproject.microservices.blog.service;

import com.javaproject.microservices.blog.dto.BlogDetailsDto;
import com.javaproject.microservices.blog.dto.BlogDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
  Page<BlogDto> getAll(Pageable page);

  BlogDto createBlog(BlogDto blogDto);

  BlogDto updateBlog(long id, BlogDto blogDto);

  BlogDetailsDto getById(long id);

  void deleteById(long id);
}
