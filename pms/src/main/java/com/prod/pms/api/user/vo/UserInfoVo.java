package com.prod.pms.api.user.vo;

import com.prod.pms.constants.Role;
import com.prod.pms.domain.user.entity.UserInfo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVo {

    private String userId;

    private String userName;

    private List<Role> userAuthority;

    private List<String> menuAuthList;

    private String email;

    private String phone;

    private String companyId;

    private String birth;

    private LocalDateTime updateDate;

    private String updateUser;

    public static UserInfoVo fromEntity(UserInfo userInfo){
        return UserInfoVo.builder()
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserKorName())
                .userAuthority(userInfo.getRoles())
                .email(userInfo.getUserEmail())
                .phone(userInfo.getUserPhone())
                .companyId(userInfo.getCompanyId())
                .birth(userInfo.getUserBirth())
                .updateUser(userInfo.getUpdateUser())
                .updateDate(userInfo.getUpdateDate())
                .build();
    }

}
