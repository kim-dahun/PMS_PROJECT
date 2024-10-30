package com.prod.pms.api.code.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.code.entity.CodeManage;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CodeModifyVo extends CmnRequestVo {

    private String codeId;
    private String codeName;
    private String textAttr01;
    private String textAttr02;
    private Double numAttr01;
    private Double numAttr02;
    private String codeType;
    private String useFlag;
    private String companyId;

    public CodeManage toEntity(){

        return CodeManage.builder()
                .codeId(codeId)
                .codeName(codeName)
                .textAttr01(textAttr01)
                .textAttr02(textAttr02)
                .numAttr01(numAttr01)
                .numAttr02(numAttr02)
                .codeType(codeType)
                .useFlag(useFlag)
                .companyId(companyId)
                .build();

    }


}
