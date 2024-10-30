package com.prod.pms.api.code.controller;

import com.prod.pms.api.code.service.CodeManageService;
import com.prod.pms.api.code.vo.CodeModifyVo;
import com.prod.pms.api.code.vo.CodeReadVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.constants.ApiConstants;
import com.prod.pms.domain.code.entity.CodeManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/code")
public class CodeManageController {

    private final CodeManageService codeManageService;

    @GetMapping("/select/all")
    public ResponseEntity<CmnResponseVo> api_getCodeManageList(CodeReadVo codeReadVo){
        return codeManageService.api_getCodeManageList(codeReadVo);
    }

    @GetMapping("/select")
    public ResponseEntity<CmnResponseVo> api_getCodeListByCodeType(CodeReadVo codeReadVo){
        return codeManageService.api_getCodeListByCodeType(codeReadVo);
    }

    @PostMapping("/modify")
    public ResponseEntity<CmnResponseVo> api_modifyCodeManage(@RequestBody List<CodeModifyVo> codeModifyVo){
        return codeManageService.api_modifyCodeManageList(codeModifyVo);
    }

}
