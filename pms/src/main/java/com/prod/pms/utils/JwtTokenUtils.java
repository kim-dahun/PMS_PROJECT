package com.prod.pms.utils;

import com.prod.pms.api.common.vo.JwtTokenVo;
import com.prod.pms.constants.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_ACCESS;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_REFRESH;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String encodedSecretKey;

    @Value("${jwt.expiration_time}")
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
        byte[] keyBytes = Decoders.BASE64.decode(encodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 토큰 생성
    public JwtTokenVo generateToken(UserDetails userDetails, String type) {

        Map<String, Object> customClaims = Map.of("userRole",userDetails.getAuthorities().stream().map(x->x.toString()).toList(),"userLocale","ko");

        byte[] keyBytes = Decoders.BASE64.decode(encodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String token = null;
        String refreshToken = null;
        switch(type){
            case TOKEN_TYPE_ACCESS -> {
                token = Jwts.builder()
                           .setClaims(customClaims)
                           .setSubject(userDetails.getUsername())
                           .setIssuedAt(new Date())
                           .setExpiration(new Date(System.currentTimeMillis() + (expiration/4)))
                           .signWith(key, SignatureAlgorithm.HS512)
                           .compact();
            }
            case TOKEN_TYPE_REFRESH -> {
                token = Jwts.builder()
                        .setClaims(customClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + (expiration/4)))
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();

                refreshToken = Jwts.builder()
                        .setClaims(customClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + expiration))
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();
            }
        }

        return JwtTokenVo.builder().accessToken(token)
                .refreshToken(refreshToken).build();


    }

    // 토큰 유효성 검사
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
