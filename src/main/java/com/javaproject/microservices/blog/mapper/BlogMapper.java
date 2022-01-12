package com.javaproject.microservices.blog.mapper;

import com.javaproject.microservices.blog.dto.BlogDto;
import com.javaproject.microservices.blog.model.Blog;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
  componentModel = "spring",
  unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BlogMapper {
  @Mapping(source = "userDto", target = "user")
  Blog toEntity(BlogDto blogDto);

  @InheritInverseConfiguration(name = "toEntity")
  BlogDto toDto(Blog blog);

  Blog mergeToEntity(BlogDto blogDto, @MappingTarget Blog blog);

  List<Blog> toEntity(Iterable<BlogDto> blogDtos);

  List<BlogDto> toDto(Iterable<Blog> blogs);
}
