package com.prod.pms.utils;

import com.prod.pms.constants.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String encodedSecretKey;

    @Value("${jwt.expiration}")
    private Long expiration;


    // 토큰에서 사용자 이름 추출
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUserLocaleFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("userLocale", String.class);
    }

    public List<Role> getUserRolesFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return ((List<String>)claims.get("userRole")).stream().map(x->Role.valueOf(x)).toList();
    }

    // 토큰에서 발행일 추출
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    // 토큰 만료 확인
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 토큰에서 만료일 추출
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 토큰에서 특정 클레임 추출
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // JWT 토큰에서 모든 클레임 추출
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(encodedSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 생성
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> customClaims = Map.of("userRole",userDetails.getAuthorities().stream().map(x->x.toString()).toList(),"userLocale","ko");

        return Jwts.builder()
                .setClaims(customClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, encodedSecretKey)
                .compact();
    }

    // 토큰 유효성 검사
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
