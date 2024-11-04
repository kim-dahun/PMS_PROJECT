package com.prod.pms.config.Security;

import com.prod.pms.domain.user.entity.UserInfo;
import com.prod.pms.utils.JwtTokenUtils;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_ACCESS;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_REFRESH;

@RequiredArgsConstructor
@Configuration
public class JwtCustomFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURL().indexOf("/user/login")!=-1){
            filterChain.doFilter(request,response);
            return;
        }

        String accessToken = null;
        String refreshToken = null;
        if(request.getCookies()!=null) {
            for (Cookie x : request.getCookies()) {
                if(x.getName().equals(TOKEN_TYPE_ACCESS)){
                    accessToken = x.getValue();
                }

                if(x.getName().equals(TOKEN_TYPE_REFRESH)){
                    refreshToken = x.getValue();
                }

            }

            String username = null;
            String companyId = request.getHeader("CompanyId");

            if (accessToken != null) {
//                accessToken = accessToken.substring(7);
                username = jwtTokenUtils.getUsernameFromToken(accessToken);
            }

//            if (refreshToken != null) {
//                refreshToken = refreshToken.substring(7);
//            }


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = userDetailsService.loadUserByUsername(username);
                UserInfo userDetails = (UserInfo) user;
                if (jwtTokenUtils.validateToken(accessToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                } else if (jwtTokenUtils.validateToken(refreshToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // 리프레시 토큰의 경우 액세스 토큰을 재발급받아서 쿠키에 갱신
                    ResponseCookie responseCookie = ResponseCookie.from(TOKEN_TYPE_ACCESS, jwtTokenUtils.generateToken(userDetails, TOKEN_TYPE_ACCESS).getAccessToken()).httpOnly(true).path("/").maxAge(60 * 10).build();

                    response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
