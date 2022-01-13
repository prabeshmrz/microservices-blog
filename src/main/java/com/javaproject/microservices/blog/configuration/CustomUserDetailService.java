package com.javaproject.microservices.blog.configuration;

import com.javaproject.microservices.blog.model.CustomUserDetail;
import com.javaproject.microservices.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    var user = userRepository.findByUsername(username);

    user.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));

    return user.map(CustomUserDetail::new).get();
  }
}
