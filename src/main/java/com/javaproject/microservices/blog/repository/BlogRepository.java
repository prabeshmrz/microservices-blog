package com.javaproject.microservices.blog.repository;

import com.javaproject.microservices.blog.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {}
