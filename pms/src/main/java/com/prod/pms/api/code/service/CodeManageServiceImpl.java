package com.prod.pms.api.code.service;

import com.prod.pms.api.code.vo.CodeManageVo;
import com.prod.pms.api.code.vo.CodeModifyVo;
import com.prod.pms.api.code.vo.CodeReadVo;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.domain.code.repository.CodeManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeManageServiceImpl implements CodeManageService{

    private final ResponseService responseService;
    private final CodeManageRepository codeManageRepository;


    @Override
    public List<CodeManageVo> getCodeManageList(CodeReadVo codeReadVo) {
        return null;
    }

    @Override
    public List<CodeManageVo> getCodeListByCodeType(String codeType, String companyId) {
        return codeManageRepository.findByCodeTypeAndUseFlagAndCompanyId(codeType, "Y" ,companyId)
                .stream().map(CodeManageVo::fromEntity).toList();
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getCodeListByCodeType(CodeReadVo codeReadVo) {
        return null;
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getCodeManageList(CodeReadVo codeReadVo) {
        return null;
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyCodeManageList(List<CodeModifyVo> codeModifyVo) {
        return null;
    }

    @Override
    public boolean modifyCodeManage(CodeModifyVo codeModifyVo) {
        return false;
    }
}
