package com.prod.pms.api.column.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.column.entity.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnModifyVo extends CmnRequestVo {

    private Long menuNo;
    private String viewId;
    private Long columnId;
    private String requireFlag;
    private String columnName;
    private String columnText;
    private String columnType;
    private String selectItemCode;
    private String editFlag;
    private String readonly;

    public Column toEntity(){

        return Column.builder()
                .columnId(columnId)
                .menuNo(menuNo)
                .viewId(viewId)
                .requireFlag(requireFlag)
                .columnName(columnName)
                .columnText(columnText)
                .colummType(columnType)
                .selectItemCode(selectItemCode)
                .editFlag(editFlag)
                .readonly(readonly)
                .build();

    }

}
