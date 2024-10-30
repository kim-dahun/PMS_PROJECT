package com.prod.pms.api.code.service;

import com.prod.pms.api.code.vo.CodeManageVo;
import com.prod.pms.api.code.vo.CodeModifyVo;
import com.prod.pms.api.code.vo.CodeReadVo;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.domain.code.entity.CodeManage;
import com.prod.pms.domain.code.repository.CodeManageRepository;
import com.prod.pms.domain.column.entity.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.prod.pms.constants.CommonConstants.*;

@Service
@RequiredArgsConstructor
public class CodeManageServiceImpl implements CodeManageService{

    private final ResponseService responseService;
    private final CodeManageRepository codeManageRepository;


    @Override
    public List<CodeManageVo> getCodeManageList(CodeReadVo codeReadVo) {
        return codeManageRepository.findAll().stream().map(CodeManageVo::fromEntity).toList();
    }


    @Override
    public List<CodeManageVo> getCodeListByCodeType(String codeType, String companyId, String useFlag) {
        return codeManageRepository.findByCodeTypeAndUseFlagAndCompanyId(codeType, useFlag ,companyId)
                .stream().map(CodeManageVo::fromEntity).toList();
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getCodeListByCodeType(CodeReadVo codeReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getCodeListByCodeType(codeReadVo.getCodeType(), codeReadVo.getCompanyId(),"Y"));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getCodeManageList(CodeReadVo codeReadVo) {

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getCodeManageList(codeReadVo));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyCodeManageList(List<CodeModifyVo> codeModifyVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            int successCnt = 0;
            for(CodeModifyVo vo : codeModifyVo){
                if(modifyCodeManage(vo)){
                    successCnt++;
                }
            }
            cmnResponseVo.setCmnResponse(successCnt==codeModifyVo.size() ? responseService.getModifySuccess() : responseService.getModifyPartiallySucceed());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    @Override
    public boolean modifyCodeManage(CodeModifyVo codeModifyVo) {
        try {
            String crudFlag = codeModifyVo.getCrudFlag();
            CodeManage codeManage = null;

            switch (crudFlag){
                case CREATE -> {
                    codeManage = codeModifyVo.toEntity();
                    codeManageRepository.save(codeManage);
                }
                case UPDATE -> {
                    codeManage = codeManageRepository.findById(codeModifyVo.getCodeId()).orElse(new CodeManage());
                    codeManage.updateCodeManage(codeModifyVo.getCodeName(), codeModifyVo.getTextAttr01(), codeModifyVo.getTextAttr02(), codeModifyVo.getNumAttr01(), codeModifyVo.getNumAttr02(), codeModifyVo.getUseFlag());
                    codeManageRepository.save(codeManage);
                }
                case DELETE -> {
                    codeManage = codeManageRepository.findById(codeModifyVo.getCodeId()).orElseThrow();
                    codeManageRepository.delete(codeManage);
                }
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
