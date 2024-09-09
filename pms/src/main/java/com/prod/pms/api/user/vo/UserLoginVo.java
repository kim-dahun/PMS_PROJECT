package com.prod.pms.api.user.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginVo {

    private String userId;

    private String userPassword;

}
