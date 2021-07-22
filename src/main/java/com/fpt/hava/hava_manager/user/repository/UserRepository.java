package com.fpt.hava.hava_manager.user.repository;

import com.fpt.hava.hava_manager.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findAllByEmail(String email);
}
