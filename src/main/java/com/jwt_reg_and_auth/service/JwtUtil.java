package com.jwt_reg_and_auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
  @Value("$(jwt.secret)")
  private String jwtSecret;

  public String generateToken(String email) {
    Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
    return Jwts.builder()
        .setSubject(email)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public String getEmailFromToken(String email) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(email).getBody();
    return claims.getSubject();
  }
}