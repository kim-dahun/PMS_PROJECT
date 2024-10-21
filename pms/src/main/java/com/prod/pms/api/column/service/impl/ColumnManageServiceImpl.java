package com.prod.pms.api.column.service.impl;

import com.prod.pms.api.code.service.CodeManageService;
import com.prod.pms.api.column.service.ColumnManageService;
import com.prod.pms.api.column.vo.ColumnManageVo;
import com.prod.pms.api.column.vo.ColumnModifyVo;
import com.prod.pms.api.column.vo.ColumnReadVo;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.domain.column.repository.ColumnRepository;
import com.prod.pms.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ColumnManageServiceImpl implements ColumnManageService {

    private final ResponseService responseService;
    private final ColumnRepository columnRepository;
    private final CodeManageService codeManageService;
    private final TokenService tokenService;
    @Override
    public List<ColumnManageVo> getColumnListByViewIdAndMenuId(ColumnReadVo columnReadVo) {
        Long menuNo = columnReadVo.getMenuNo();
        String viewId = columnReadVo.getViewId();
        return columnRepository.findByMenuNoAndViewId(menuNo, viewId).stream().map(ColumnManageVo::fromEntity).map(this::getRefineColumnSource).toList();
    }

    public ColumnManageVo getRefineColumnSource(ColumnManageVo columnManageVo){
        columnManageVo.setEditable(columnManageVo.getEditFlag()!=null && columnManageVo.getEditFlag().equals("Y"));
        columnManageVo.setRequire(columnManageVo.getRequireFlag()!=null && columnManageVo.getRequireFlag().equals("Y"));
        columnManageVo.setReadonly(columnManageVo.getReadonlyFlag()!=null && columnManageVo.getReadonlyFlag().equals("Y"));
        columnManageVo.setOptions(codeManageService.getCodeListByCodeType(columnManageVo.getColumnType(), tokenService.getCompanyId()));
        return columnManageVo;
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getColumnData(ColumnReadVo columnReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getColumnListByViewIdAndMenuId(columnReadVo));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyColumnData(List<ColumnModifyVo> columnManageVo) {
        return null;
    }

    @Override
    public boolean modifyColumn(ColumnModifyVo columnModifyVo) {
        return false;
    }
}
