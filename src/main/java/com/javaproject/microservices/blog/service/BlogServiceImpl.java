package com.javaproject.microservices.blog.service;

import com.javaproject.microservices.blog.dto.BlogDetailsDto;
import com.javaproject.microservices.blog.dto.BlogDto;
import com.javaproject.microservices.blog.dto.UserDto;
import com.javaproject.microservices.blog.mapper.BlogMapper;
import com.javaproject.microservices.blog.model.Blog;
import com.javaproject.microservices.blog.repository.BlogRepository;
import com.javaproject.microservices.blog.utils.AuthenticationUtils;
import com.javaproject.microservices.blog.utils.exception.DevGlobalException;
import com.javaproject.microservices.blog.utils.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final BlogRepository blogRepository;

  private final BlogMapper blogMapper;

  private final RestTemplate restTemplate;

  @Override
  public Page<BlogDto> getAll(Pageable page) {
    return blogRepository.findAll(page).map(blogMapper::toDto);
  }

  @Override
  public BlogDto createBlog(BlogDto blogDto) {
    var blog = blogMapper.toEntity(blogDto);
    blog.setUsername(AuthenticationUtils.getUserId(true));
    return blogMapper.toDto(blogRepository.save(blog));
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
  public BlogDetailsDto getById(long id) {
    var blog = findById(id);
    var blogDto = blogMapper.toDetailsDto(blog);
    var userDto = restTemplate.getForObject(
      "http://user-service/user/" + blog.getUsername(),
      UserDto.class
    );
    blogDto.setUserDto(userDto);
    return blogDto;
  }

  @Override
  public void deleteById(long id) {
    findById(id);
    blogRepository.deleteById(id);
  }
}
