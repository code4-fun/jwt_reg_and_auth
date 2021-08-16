package com.jwt_reg_and_auth.service;

import com.jwt_reg_and_auth.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public static CustomUserDetails fromUserToCustomUserDetails(User user) {
    CustomUserDetails customUserDetails = new CustomUserDetails();
    customUserDetails.email = user.getEmail();
    customUserDetails.password = user.getPassword();
    customUserDetails.grantedAuthorities = Collections
        .singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
    return customUserDetails;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}