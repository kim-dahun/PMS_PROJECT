package com.prod.pms.api.code.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

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

}
