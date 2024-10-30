package com.prod.pms.api.user.service;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.user.vo.UserInfoModifyVo;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.api.user.vo.UserLoginVo;
import com.prod.pms.domain.user.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    ResponseEntity<CmnResponseVo> getUserList(UserInfoVo userInfoVo);

    ResponseEntity<CmnResponseVo> getUserInfoApi(UserLoginVo userLoginVo, HttpServletRequest request);

    UserInfo getUserInfo(String username, String userPassword);

    UserInfoVo getUserSimpleInfo(String userId);

    ResponseEntity<CmnResponseVo> modifyUserInfo(UserInfoModifyVo userInfoModifyVo);


}
