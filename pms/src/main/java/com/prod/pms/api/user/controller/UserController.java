package com.prod.pms.api.user.controller;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.user.service.UserService;
import com.prod.pms.api.user.vo.UserInfoModifyVo;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.api.user.vo.UserLoginVo;
import com.prod.pms.constants.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<CmnResponseVo> api_getUserLoginInfo(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request){
        return userService.getUserInfoApi(userLoginVo,request);
    }

    @PostMapping("/create")
    public ResponseEntity<CmnResponseVo> api_insertUserInfo(@RequestBody UserInfoModifyVo cmnRequestVo){
        return userService.modifyUserInfo(cmnRequestVo);
    }

    @PostMapping("/update")
    public ResponseEntity<CmnResponseVo> api_updateUserInfo(@RequestBody UserInfoModifyVo cmnRequestVo){
        return userService.modifyUserInfo(cmnRequestVo);
    }

    @GetMapping("/select")
    public ResponseEntity<CmnResponseVo> api_getUserList(UserInfoVo userInfoVo){
        return userService.getUserList(userInfoVo);
    }

}
