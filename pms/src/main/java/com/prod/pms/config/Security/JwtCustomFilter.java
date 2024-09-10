package com.prod.pms.config.Security;

import com.prod.pms.constants.JwtConstants;
import com.prod.pms.domain.user.entity.UserInfo;
import com.prod.pms.utils.JwtTokenUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_ACCESS;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_REFRESH;

@RequiredArgsConstructor
@Configuration
public class JwtCustomFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = null;
        String refreshToken = null;
        for(Cookie x : request.getCookies()){
            accessToken = x.getAttribute(TOKEN_TYPE_ACCESS);
            refreshToken = x.getAttribute(TOKEN_TYPE_REFRESH);
        }

        String username = null;


        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(accessToken);
        }

        if (refreshToken != null && refreshToken.startsWith("Bearer ")){
            refreshToken = refreshToken.substring(7);
        }



        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(accessToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } else if(jwtTokenUtil.validateToken(refreshToken, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // 리프레시 토큰의 경우 액세스 토큰을 재발급받아서 쿠키에 갱신
                ResponseCookie responseCookie = ResponseCookie.from(TOKEN_TYPE_ACCESS, jwtTokenUtil.generateToken(userDetails, TOKEN_TYPE_ACCESS).getAccessToken()).httpOnly(true).path("/").maxAge(60*10).build();

                response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

            }
        }

        filterChain.doFilter(request, response);
    }

}
