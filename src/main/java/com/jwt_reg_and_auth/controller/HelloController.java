package com.jwt_reg_and_auth.controller;

import com.jwt_reg_and_auth.entity.User;
import com.jwt_reg_and_auth.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
  @NonNull
  private final UserService userService;

  @GetMapping("hello")
  public String helloAuthorizedUser(Authentication authentication){
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    String username = principal.getUsername();
    User user = userService.findByEmail(username);
    return "Hello, " + (user != null ? user.getFirstName() : "");
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(Exception.class)
  public String return401(Exception ex) {
    return ex.getMessage();
  }
}