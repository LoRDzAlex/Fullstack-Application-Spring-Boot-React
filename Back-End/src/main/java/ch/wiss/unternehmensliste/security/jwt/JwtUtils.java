package ch.wiss.unternehmensliste.security.jwt;

import ch.wiss.unternehmensliste.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Die JwtUtils Klasse wird verwendet, um JWTs zu generieren, den Username aus einem JWT zu extrahieren und zu validieren.
 * Die Klasse verwendet die io.jsonwebtoken.Jwts Klasse.
 */
@Component
public class JwtUtils {
    private static final Logger logger =
            LoggerFactory.getLogger(JwtUtils.class);
    // Die Werte für den JWT Secret und die JWT Expiration Time werden aus der application.properties Datei gelesen.
    @Value("${jobapplication.app.jwtSecret}")
    private String jwtSecret;
    // Die JWT Expiration Time wird in Millisekunden angegeben.
    @Value("${jobapplication.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    // Die Methode generateJwtToken() wird verwendet, um einen JWT zu generieren.
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    // Die Methode getUserNameFromJwtToken() wird verwendet, um den Username aus einem JWT zu extrahieren.
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }
    // Die Methode validateJwtToken() wird verwendet, um einen JWT zu validieren.
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
