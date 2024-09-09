package com.prod.pms.api.user.vo;

import com.prod.pms.domain.user.entity.UserInfo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoValidVo {

    private boolean isExist;

    private boolean isAvailable;

    private UserInfo userInfo;

}
