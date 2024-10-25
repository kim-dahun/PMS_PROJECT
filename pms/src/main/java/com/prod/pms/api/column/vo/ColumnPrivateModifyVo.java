package com.prod.pms.api.column.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.column.entity.ColumnManage;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColumnPrivateModifyVo extends CmnRequestVo {

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

    private Long columnManageId;
    private String userId;
    private String userType;
    private Long columnSeq;

    public ColumnManage toEntity(){

        return ColumnManage.builder()
                .columnId(columnId)
                .menuNo(menuNo)
                .viewId(viewId)
                .requireFlag(requireFlag)
                .columnText(columnText)
                .colummType(columnType)
                .selectItemCode(selectItemCode)
                .editFlag(editFlag)
                .readonly(readonly)
                .columnSeq(columnSeq)
                .columnManageId(columnManageId)
                .userId(userId)
                .userType(userType)
                .build();

    }

}
