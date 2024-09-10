package com.prod.pms.api.user.controller;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.user.service.UserService;
import com.prod.pms.constants.ApiConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<CmnResponseVo> api_getUserLoginInfo(@RequestBody CmnRequestVo cmnRequestVo, HttpServletRequest request){
        return userService.getUserInfoApi(cmnRequestVo.getUserLoginVo(),request);
    }

    @PostMapping("/create")
    public ResponseEntity<CmnResponseVo> api_insertUserInfo(@RequestBody CmnRequestVo cmnRequestVo){
        return userService.modifyUserInfo(cmnRequestVo);
    }

    @PostMapping("/update")
    public ResponseEntity<CmnResponseVo> api_updateUserInfo(@RequestBody CmnRequestVo cmnRequestVo){
        return userService.modifyUserInfo(cmnRequestVo);
    }

}
