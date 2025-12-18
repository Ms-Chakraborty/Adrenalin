package com.titiksha.tickets.util;

import java.util.UUID;
import org.springframework.security.oauth2.jwt.Jwt;

public final class JwtUtil {

  private JwtUtil() {}

  public static UUID parseUserId(Jwt jwt) {
    if (jwt == null || jwt.getSubject() == null) {
      throw new IllegalArgumentException("JWT or subject is null");
    }
    return UUID.fromString(jwt.getSubject());
  }
}
