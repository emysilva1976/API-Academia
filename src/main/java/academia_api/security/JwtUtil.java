package academia_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "minha_chave_super_secreta_que_deve_ser_grande_123456";
    private static final long EXPIRATION = 1000L * 60 * 60; // 1 hora

    private static Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public static String gerarToken(String usuario, String role) {
        return Jwts.builder()
                .setSubject(usuario)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String getRoleFromToken(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}
