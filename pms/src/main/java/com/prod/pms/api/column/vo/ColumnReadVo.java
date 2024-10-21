package com.prod.pms.api.column.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColumnReadVo extends CmnRequestVo {

    private Long menuNo;

    private String viewId;

}
