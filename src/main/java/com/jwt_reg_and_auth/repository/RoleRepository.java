package com.jwt_reg_and_auth.repository;

import com.jwt_reg_and_auth.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
  Role findByName(String name);
}