package com.prod.pms.api.column.service.impl;

import com.prod.pms.api.code.service.CodeManageService;
import com.prod.pms.api.column.service.ColumnManageService;
import com.prod.pms.api.column.vo.*;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.menu.vo.MenuModifyVo;
import com.prod.pms.domain.column.entity.Column;
import com.prod.pms.domain.column.entity.ColumnManage;
import com.prod.pms.domain.column.repository.ColumnManageRepository;
import com.prod.pms.domain.column.repository.ColumnRepository;
import com.prod.pms.domain.menu.entity.MenuList;
import com.prod.pms.utils.JwtTokenUtils;
import com.prod.pms.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.prod.pms.constants.CommonConstants.*;
import static com.prod.pms.utils.ObjectUtils.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ColumnManageServiceImpl implements ColumnManageService {

    private final ResponseService responseService;
    private final ColumnRepository columnRepository;
    private final ColumnManageRepository columnManageRepository;
    private final CodeManageService codeManageService;
    private final TokenService tokenService;
    @Override
    public List<ColumnManageVo> getColumnListByViewIdAndMenuId(ColumnReadVo columnReadVo) {
        Long menuNo = columnReadVo.getMenuNo();
        String viewId = columnReadVo.getViewId();
        return columnRepository.findByMenuNoAndViewId(menuNo, viewId).stream().map(ColumnManageVo::fromEntity).map(this::getRefineColumnSource).toList();
    }

    public ColumnManageVo getRefineColumnSource(ColumnManageVo columnManageVo){
        columnManageVo.setEditable(isNotNullAndIsUsed(columnManageVo.getEditFlag()));
        columnManageVo.setRequire(isNotNullAndIsUsed(columnManageVo.getRequireFlag()));
        columnManageVo.setReadonly(isNotNullAndIsUsed(columnManageVo.getReadonlyFlag()));
        columnManageVo.setOptions(codeManageService.getCodeListByCodeType(columnManageVo.getColumnType(), tokenService.getCompanyId(), "Y"));
        return columnManageVo;
    }

    public ColumnPrivateDataVo getRefineColumnSource(ColumnPrivateDataVo columnManageVo){
        columnManageVo.setEditable(isNotNullAndIsUsed(columnManageVo.getEditFlag()));
        columnManageVo.setRequire(isNotNullAndIsUsed(columnManageVo.getRequireFlag()));
        columnManageVo.setReadonly(isNotNullAndIsUsed(columnManageVo.getReadonlyFlag()));
        columnManageVo.setOptions(codeManageService.getCodeListByCodeType(columnManageVo.getColumnType(), tokenService.getCompanyId(),"Y"));
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
        CmnResponseVo cmnResponseVo = new CmnResponseVo();

        try {
            int successCnt = 0;
            for(ColumnModifyVo columnModifyVo : columnManageVo){
                if(modifyColumn(columnModifyVo)){
                    successCnt++;
                }
            }
            Map<String, Object> resultMap = Map.of("totalCnt",columnManageVo.size(),"successCnt",successCnt);
            cmnResponseVo.setResultData(resultMap);
            cmnResponseVo.setCmnResponse(successCnt==columnManageVo.size() ? responseService.getModifySuccess() : responseService.getModifyPartiallySucceed());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public boolean modifyColumn(ColumnModifyVo columnModifyVo) {

        try {
            String crudFlag = columnModifyVo.getCrudFlag();
            Column column = null;

            switch (crudFlag){
                case CREATE -> {
                    column = columnModifyVo.toEntity();
                    columnRepository.save(column);
                }
                case UPDATE -> {
                    column = columnRepository.findById(columnModifyVo.getColumnId()).orElse(new Column());
                    column.updateEntity(columnModifyVo.getRequireFlag(), columnModifyVo.getEditFlag(), columnModifyVo.getReadonly(), columnModifyVo.getColumnName(), columnModifyVo.getColumnText(), columnModifyVo.getColumnType(), columnModifyVo.getSelectItemCode());
                    columnRepository.save(column);
                }
                case DELETE -> {
                    column = columnRepository.findById(columnModifyVo.getColumnId()).orElseThrow();
                    columnRepository.delete(column);
                }
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean modifyPrivateColumn(ColumnPrivateModifyVo columnModifyVo) {
        try {
            String crudFlag = columnModifyVo.getCrudFlag();
            ColumnManage columnManage = null;

            switch (crudFlag){
                case CREATE -> {
                    columnManage = columnModifyVo.toEntity();
                    columnManageRepository.save(columnManage);
                }
                case UPDATE -> {
                    columnManage = columnManageRepository.findById(columnModifyVo.getColumnId()).orElse(new ColumnManage());
                    columnManage.updateColumnManage(columnModifyVo.getColumnSeq(),columnModifyVo.getEditFlag(), columnModifyVo.getReadonly(), columnModifyVo.getRequireFlag(), columnModifyVo.getColumnText());
                    columnManageRepository.save(columnManage);
                }
                case DELETE -> {
                    columnManage = columnManageRepository.findById(columnModifyVo.getColumnId()).orElseThrow();
                    columnManageRepository.delete(columnManage);
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }


    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyPrivateColumnData(List<ColumnPrivateModifyVo> columnModifyVoList) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();

        try {
            int successCnt = 0;
            for(ColumnPrivateModifyVo columnModifyVo : columnModifyVoList){
                if(modifyPrivateColumn(columnModifyVo)){
                    successCnt++;
                }
            }
            Map<String, Object> resultMap = Map.of("totalCnt",columnModifyVoList.size(),"successCnt",successCnt);
            cmnResponseVo.setResultData(resultMap);
            cmnResponseVo.setCmnResponse(successCnt==columnModifyVoList.size() ? responseService.getModifySuccess() : responseService.getModifyPartiallySucceed());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getColumnData(ColumnMngReadVo columnMngReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getColumnManageListByViewIdAndMenuIdAndUserId(columnMngReadVo));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public List<ColumnPrivateDataVo> getColumnManageListByViewIdAndMenuIdAndUserId(ColumnMngReadVo columnMngReadVo) {
        Long menuNo = columnMngReadVo.getMenuNo();
        String viewId = columnMngReadVo.getViewId();
        String userId = columnMngReadVo.getUserId();
        return columnManageRepository.findByViewIdAndMenuNoAndUserId(viewId, menuNo,userId ).stream().map(ColumnPrivateDataVo::fromEntity).map(this::getRefineColumnSource).toList();
    }
}
