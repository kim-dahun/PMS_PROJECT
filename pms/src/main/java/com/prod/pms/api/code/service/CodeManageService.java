package com.prod.pms.api.code.service;

import com.prod.pms.api.code.vo.CodeManageVo;
import com.prod.pms.api.code.vo.CodeModifyVo;
import com.prod.pms.api.code.vo.CodeReadVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CodeManageService {

    List<CodeManageVo> getCodeManageList(CodeReadVo codeReadVo);

    List<CodeManageVo> getCodeListByCodeType(String codeType, String companyId);

    ResponseEntity<CmnResponseVo> api_getCodeListByCodeType(CodeReadVo codeReadVo);

    ResponseEntity<CmnResponseVo> api_getCodeManageList(CodeReadVo codeReadVo);

    ResponseEntity<CmnResponseVo> api_modifyCodeManageList(List<CodeModifyVo> codeModifyVo);

    boolean modifyCodeManage(CodeModifyVo codeModifyVo);



}
