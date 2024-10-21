package com.prod.pms.api.common.service;

import com.prod.pms.api.common.vo.JwtTokenVo;
import com.prod.pms.constants.Role;
import com.prod.pms.domain.user.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface TokenService {

    public String getUserIdByToken();

    public String getUserLocale();

    public List<Role> getUserRoles();

    String getCompanyId();


    JwtTokenVo getAccessToken(UserInfo userDetails);

    JwtTokenVo getRefreshToken(UserInfo userDetails);
}
