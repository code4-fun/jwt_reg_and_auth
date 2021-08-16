package com.jwt_reg_and_auth.repository;

import com.jwt_reg_and_auth.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  User findByEmail(String email);
}