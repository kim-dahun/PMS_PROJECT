package com.prod.pms.api.column.service;

import com.prod.pms.api.column.vo.*;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.domain.column.entity.Column;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ColumnManageService {

    // 컬럼 불러오기
    List<ColumnManageVo> getColumnListByViewIdAndMenuId(ColumnReadVo columnReadVo);

    ResponseEntity<CmnResponseVo> api_getColumnData(ColumnReadVo columnReadVo);

    ResponseEntity<CmnResponseVo> api_modifyColumnData(List<ColumnModifyVo> columnManageVo);

    boolean modifyColumn(ColumnModifyVo columnModifyVo);

    boolean modifyPrivateColumn(ColumnPrivateModifyVo columnModifyVo);

    ResponseEntity<CmnResponseVo> api_modifyPrivateColumnData(List<ColumnPrivateModifyVo> columnModifyVoList);

    ResponseEntity<CmnResponseVo> api_getColumnData(ColumnMngReadVo columnMngReadVo);

    List<ColumnPrivateDataVo> getColumnManageListByViewIdAndMenuIdAndUserId(ColumnMngReadVo columnMngReadVo);

}
