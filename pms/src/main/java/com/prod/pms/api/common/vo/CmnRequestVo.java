package com.prod.pms.api.common.vo;

import com.prod.pms.api.user.vo.UserInfoModifyVo;
import com.prod.pms.api.user.vo.UserInfoValidVo;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.api.user.vo.UserLoginVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CmnRequestVo {

    private String crudFlag;

    // UserList 관련 객체
    private UserInfoModifyVo userInfoModifyVo;
    private UserInfoValidVo userInfoValidVo;
    private UserInfoVo userInfoVo;
    private UserLoginVo userLoginVo;



}