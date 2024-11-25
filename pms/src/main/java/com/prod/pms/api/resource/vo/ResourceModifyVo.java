package com.prod.pms.api.resource.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.constants.CommonConstants;
import com.prod.pms.domain.resource.entity.ResourceManage;
import com.prod.pms.domain.resource.entity.id.ResourceManageId;
import lombok.*;

import java.time.LocalDateTime;

import static com.prod.pms.constants.CommonConstants.CREATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ResourceModifyVo extends CmnRequestVo {

    private String resourceCode;
    private String companyId;
    private String resourceName;
    private String resourceDesc;
    private String resourceType;
    private Long resourceCost;

    private String createUser;
    private LocalDateTime createDate;
    private String updateUser;
    private LocalDateTime updateDate;

    public ResourceManage toEntity(){
        return ResourceManage.builder()
                .resourceCost(resourceCost)
                .resourceDesc(resourceDesc)
                .resourceName(resourceName)
                .resourceType(resourceType)
                .id(ResourceManageId.builder().resourceCode(resourceCode).companyId(companyId).build())
                .createUser(getRequestId())
                .updateUser(getRequestId())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

    public static ResourceModifyVo fromEntity(ResourceManage resourceManage){

        return ResourceModifyVo.builder()
                .resourceCode(resourceManage.getId().getResourceCode())
                .companyId(resourceManage.getId().getCompanyId())
                .resourceDesc(resourceManage.getResourceDesc())
                .resourceName(resourceManage.getResourceName())
                .resourceType(resourceManage.getResourceType())
                .resourceCost(resourceManage.getResourceCost())
                .createUser(resourceManage.getCreateUser())
                .createDate(resourceManage.getCreateDate())
                .updateDate(resourceManage.getUpdateDate())
                .updateUser(resourceManage.getUpdateUser())
                .build();

    }


}
