package com.prod.pms.api.code.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeReadVo extends CmnRequestVo {

    private String codeType;
    private String useFlag;
    private String companyId;

}
