package kr.freesol.cafeconnect.security;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("spring.jwt.secret-key")
    private final String secretKeyString;
    @Value("spring.jwt.expiration-hours")
    private final long expirationHours;

    public String generateToken(String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + hoursToMilliseconds(expirationHours));
        return Jwts.builder()
                .claim("email", email)
                .issuedAt(now)
                .expiration(expirationTime)
                .signWith(stringToSecretKey(secretKeyString))
                .compact();
    }

    public boolean validateToken(String token) {
        return Jwts.parser()
                .verifyWith(stringToSecretKey(secretKeyString))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    private SecretKeySpec stringToSecretKey(String secretKeyString) {
        return new SecretKeySpec(secretKeyString.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    private long hoursToMilliseconds(long hours) {
        return hours * 1000 * 60 * 60;
    }
}
