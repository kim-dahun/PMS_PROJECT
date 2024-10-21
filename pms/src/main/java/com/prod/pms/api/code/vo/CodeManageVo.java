package com.prod.pms.api.code.vo;

import com.prod.pms.domain.code.entity.CodeManage;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeManageVo {

    private String codeId;
    private String codeName;
    private String textAttr01;
    private String textAttr02;
    private Double numAttr01;
    private Double numAttr02;
    private String codeType;
    private String useFlag;
    private String companyId;

    public static CodeManageVo fromEntity(CodeManage codeManage){

        return CodeManageVo.builder()
                .codeId(codeManage.getCodeId())
                .codeName(codeManage.getCodeName())
                .codeType(codeManage.getCodeType())
                .textAttr01(codeManage.getTextAttr01())
                .textAttr02(codeManage.getTextAttr02())
                .numAttr01(codeManage.getNumAttr01())
                .numAttr02(codeManage.getNumAttr02())
                .useFlag(codeManage.getUseFlag())
                .companyId(codeManage.getCompanyId())
                .build();

    }


}
