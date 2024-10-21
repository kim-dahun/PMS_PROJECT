package com.prod.pms.api.column.vo;

import com.prod.pms.api.code.vo.CodeManageVo;
import com.prod.pms.domain.code.entity.CodeManage;
import com.prod.pms.domain.column.entity.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnManageVo {

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

    public static ColumnManageVo fromEntity(Column column){
        return ColumnManageVo.builder()
                .columnId(column.getColumnId())
                .menuNo(column.getMenuNo())
                .viewId(column.getViewId())
                .columnType(column.getColummType())
                .columnName(column.getColumnName())
                .columnText(column.getColumnText())
                .editFlag(column.getEditFlag())
                .readonlyFlag(column.getReadonly())
                .selectItemCode(column.getSelectItemCode())
                .requireFlag(column.getRequireFlag())
                .build();
    }

}
