package com.prod.pms.api.column.controller;

import com.prod.pms.api.column.service.ColumnManageService;
import com.prod.pms.api.column.vo.ColumnMngReadVo;
import com.prod.pms.api.column.vo.ColumnModifyVo;
import com.prod.pms.api.column.vo.ColumnPrivateModifyVo;
import com.prod.pms.api.column.vo.ColumnReadVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/column")
public class ColumnManageController {

    private final ColumnManageService columnManageService;

    @GetMapping("/select")
    public ResponseEntity<CmnResponseVo> api_getColumnList( ColumnReadVo columnReadVo){
        return columnManageService.api_getColumnData(columnReadVo);
    }

    @PostMapping("/modify")
    public ResponseEntity<CmnResponseVo> api_modifyColumn(@RequestBody List<ColumnModifyVo> columnModifyVoList){
        return columnManageService.api_modifyColumnData(columnModifyVoList);
    }

}
