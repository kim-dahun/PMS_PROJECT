package com.prod.pms.api.column.controller;

import com.prod.pms.api.column.service.ColumnManageService;
import com.prod.pms.api.column.vo.ColumnMngReadVo;
import com.prod.pms.api.column.vo.ColumnPrivateModifyVo;
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
@RequestMapping(ApiConstants.API_URL + "/user-column")
public class UserColumnController {

    private final ColumnManageService columnManageService;
    @GetMapping("/select")
    public ResponseEntity<CmnResponseVo> api_getPrivateColumnList(ColumnMngReadVo columnReadVo){
        return columnManageService.api_getColumnData(columnReadVo);
    }

    @PostMapping("/modify")
    public ResponseEntity<CmnResponseVo> api_modifyPrivateColumn(@RequestBody List<ColumnPrivateModifyVo> columnModifyVoList){
        return columnManageService.api_modifyPrivateColumnData(columnModifyVoList);
    }
}
