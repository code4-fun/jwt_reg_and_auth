package com.jwt_reg_and_auth.controller;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
  @NotEmpty
  private String email;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String password;
  private String lastName;
}