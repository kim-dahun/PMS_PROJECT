package com.prod.pms.api.common.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CmnResponseVo {

    private String statusCode;
    private String message;
    private Object resultData;
    private String etc;

}
