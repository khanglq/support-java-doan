package com.fpt.hava.hava_manager.user.service;

import com.fpt.hava.hava_manager.user.domain.UserDetailsImpl;
import com.fpt.hava.hava_manager.user.domain.UserEntity;
import com.fpt.hava.hava_manager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findAllByEmail(s);

    if (userEntity == null) throw new UsernameNotFoundException(s);

    return new UserDetailsImpl(userEntity);
  }
}
