package com.prod.pms.api.user.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.user.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoModifyVo extends CmnRequestVo {

    String userId;

    String password;

    String phone;

    String email;

    String birth;

    String companyId;

    String name;

    String useFlag;

    String userRole;

    public UserInfo toEntity() {
        return UserInfo.builder()
                .userPassword(password)
                .companyId(companyId)
                .userId(userId)
                .userEmail(email)
                .userPhone(phone)
                .userBirth(birth)
                .userKorName(name)
                .useFlag(useFlag)
                .build();
    }



}
