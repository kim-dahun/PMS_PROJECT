package com.prod.pms.api.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CmnResponseVo {

    private String statusCode;

    private String message;

    private Object resultData;

    private String etc;

}
