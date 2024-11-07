package com.prod.pms.domain.resource.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import com.prod.pms.domain.resource.entity.id.ResourceManageId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "RESOURCE_MANAGE")
public class ResourceManage extends CmnBaseCUDEntity {
    @EmbeddedId
    private ResourceManageId id;

    @Column(name = "RESOURCE_NAME", length = 40)
    private String resourceName;

    @Column(name = "RESOURCE_TYPE", length = 40)
    private String resourceType;

    @Column(name = "RESOURCE_COST")
    private Long resourceCost;

    @Column(name = "RESOURCE_DESC", length = 400)
    private String resourceDesc;

}