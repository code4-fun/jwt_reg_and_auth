package com.jwt_reg_and_auth.service;

import static org.springframework.util.StringUtils.hasText;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

@Service
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
  @NonNull
  private final JwtUtil jwtUtil;
  @NonNull
  private final CustomUserDetailsService customUserDetailsService;

  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {

    String token = getTokenFromRequest((HttpServletRequest) servletRequest);
    if (token != null && jwtUtil.validateToken(token)) {
      String userEmail = jwtUtil.getEmailFromToken(token);
      CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userEmail);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails,
          null, customUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }
}