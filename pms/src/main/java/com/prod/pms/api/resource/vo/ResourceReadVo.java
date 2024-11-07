package com.prod.pms.api.resource.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResourceReadVo extends CmnRequestVo {

    private String companyId;
    private String projectCd;
    private String resourceDesc;
    private String resourceName;
    private String startDate;
    private String endDate;

}
