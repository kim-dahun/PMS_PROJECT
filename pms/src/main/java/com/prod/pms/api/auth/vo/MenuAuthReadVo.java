package com.prod.pms.api.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuAuthReadVo {

    private Long menuNo;
    private String userId;

}
