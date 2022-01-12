package com.javaproject.microservices.blog.controller;

import com.javaproject.microservices.blog.dto.BlogDto;
import com.javaproject.microservices.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BlogController.CONTROLLER_URL)
@RequiredArgsConstructor
public class BlogController {

  public static final String CONTROLLER_URL = "blog";

  private final BlogService blogService;

  @GetMapping
  public ResponseEntity<Page<BlogDto>> getBlogPages(
    @PageableDefault Pageable pageable
  ) {
    return new ResponseEntity<>(blogService.getAll(pageable), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto) {
    return new ResponseEntity<BlogDto>(
      blogService.createBlog(blogDto),
      HttpStatus.CREATED
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<BlogDto> updateBlog(
    @PathVariable(value = "id") long id,
    @RequestBody BlogDto blogDto
  ) {
    return new ResponseEntity<BlogDto>(
      blogService.updateBlog(id, blogDto),
      HttpStatus.OK
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<BlogDto> getBlogDetail(
    @PathVariable(value = "id") long id
  ) {
    return new ResponseEntity<BlogDto>(blogService.getById(id), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<BlogDto> deleteBlog(
    @PathVariable(value = "id") long id
  ) {
    blogService.deleteById(id);
    return new ResponseEntity<BlogDto>(HttpStatus.OK);
  }
}
