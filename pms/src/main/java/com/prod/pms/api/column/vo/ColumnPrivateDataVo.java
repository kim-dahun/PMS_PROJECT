package com.prod.pms.api.column.vo;

import com.prod.pms.api.code.vo.CodeManageVo;
import com.prod.pms.domain.column.entity.Column;
import com.prod.pms.domain.column.entity.ColumnManage;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ColumnPrivateDataVo {

    private Long menuNo;
    private String viewId;
    private Long columnId;
    private String requireFlag;
    private String columnName;
    private String columnText;
    private String columnType;
    private String selectItemCode;
    private String editFlag;
    private String readonlyFlag;

    private List<CodeManageVo> options;
    private Boolean require;
    private Boolean editable;
    private Boolean readonly;
    private String formatter;

    private String userId;
    private String userType;
    private Long columnManageId;
    private Long columnSeq;

    public static ColumnPrivateDataVo fromEntity(ColumnManage column){
        return ColumnPrivateDataVo.builder()
                .columnId(column.getColumnId())
                .userType(column.getUserType())
                .columnSeq(column.getColumnSeq())
                .menuNo(column.getMenuNo())
                .userId(column.getUserId())
                .viewId(column.getViewId())
                .columnType(column.getColummType())
                .columnManageId(column.getColumnManageId())
                .columnText(column.getColumnText())
                .editFlag(column.getEditFlag())
                .readonlyFlag(column.getReadonly())
                .selectItemCode(column.getSelectItemCode())
                .requireFlag(column.getRequireFlag())
                .build();
    }

}
