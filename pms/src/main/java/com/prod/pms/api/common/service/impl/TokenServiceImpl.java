package com.prod.pms.api.common.service.impl;

import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.JwtTokenVo;
import com.prod.pms.constants.JwtConstants;
import com.prod.pms.constants.Role;
import com.prod.pms.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

import static com.prod.pms.constants.JwtConstants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtTokenUtil jwtTokenUtil;
    private String token;


    public String getNowToken(){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        return request.getHeader(TOKEN_HEADER);
    }

    @Override
    public String getUserIdByToken() {

        token = this.getNowToken();

        return jwtTokenUtil.getUsernameFromToken(token);
    }

    @Override
    public String getUserLocale() {

        token = this.getNowToken();

        return jwtTokenUtil.getUserLocaleFromToken(token);
    }

    @Override
    public List<Role> getUserRoles() {
        token = this.getNowToken();

        return jwtTokenUtil.getUserRolesFromToken(token);
    }

    @Override
    public JwtTokenVo getAccessToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails, TOKEN_TYPE_ACCESS);
    }

    @Override
    public JwtTokenVo getRefreshToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails, TOKEN_TYPE_REFRESH);
    }
}
