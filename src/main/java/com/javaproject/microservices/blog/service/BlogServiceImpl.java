package com.javaproject.microservices.blog.service;

import com.javaproject.microservices.blog.dto.BlogDto;
import com.javaproject.microservices.blog.mapper.BlogMapper;
import com.javaproject.microservices.blog.model.Blog;
import com.javaproject.microservices.blog.repository.BlogRepository;
import com.javaproject.microservices.blog.utils.exception.DevGlobalException;
import com.javaproject.microservices.blog.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final BlogRepository blogRepository;

  private final BlogMapper blogMapper;

  @Override
  public Page<BlogDto> getAll(Pageable page) {
    return blogRepository.findAll(page).map(blogMapper::toDto);
  }

  @Override
  public BlogDto createBlog(BlogDto blogDto) {
    return blogMapper.toDto(blogRepository.save(blogMapper.toEntity(blogDto)));
  }

  public Blog findById(long id) {
    return blogRepository
      .findById(id)
      .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public BlogDto updateBlog(long id, BlogDto blogDto) {
    var blog = findById(id);

    if (blogDto.getId() != blog.getId()) throw new DevGlobalException(
      HttpStatus.CONFLICT,
      "Resource You Are Trying To Update Doesnot Matches!!"
    );

    var updatedBlog = blogMapper.mergeToEntity(blogDto, blog);

    return blogMapper.toDto(blogRepository.save(updatedBlog));
  }

  @Override
  public BlogDto getById(long id) {
    return blogMapper.toDto(findById(id));
  }

  @Override
  public void deleteById(long id) {
    findById(id);
    blogRepository.deleteById(id);
  }
}
