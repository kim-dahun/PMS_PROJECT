package com.prod.pms.api.column.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColumnMngReadVo extends CmnRequestVo {

    private Long menuNo;
    private String viewId;
    private String userId;
    private Long columnMngId;
    private String userType;

}
