package com.prod.pms.api.common.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtTokenVo {

    String accessToken;

    String refreshToken;

}
