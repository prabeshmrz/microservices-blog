package com.javaproject.microservices.blog.mapper;

import java.util.List;

import com.javaproject.microservices.blog.dto.BlogDetailsDto;
import com.javaproject.microservices.blog.dto.BlogDto;
import com.javaproject.microservices.blog.model.Blog;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BlogMapper {
  Blog toEntity(BlogDto blogDto);

  BlogDto toDto(Blog blog);

  BlogDetailsDto toDetailsDto(Blog blog);

  Blog mergeToEntity(BlogDto blogDto, @MappingTarget Blog blog);

  List<Blog> toEntity(Iterable<BlogDto> blogDtos);

  List<BlogDto> toDto(Iterable<Blog> blogs);
}
