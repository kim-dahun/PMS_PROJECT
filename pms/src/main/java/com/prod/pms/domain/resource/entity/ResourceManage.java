package com.prod.pms.domain.resource.entity;

import com.prod.pms.domain.resource.entity.id.ResourceManageId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RESOURCE_MANAGE")
public class ResourceManage {
    @EmbeddedId
    private ResourceManageId id;

    @Column(name = "RESOURCE_NAME", length = 40)
    private String resourceName;

    @Column(name = "RESOURCE_TYPE", length = 40)
    private String resourceType;

    @Column(name = "RESOURCE_COST")
    private Integer resourceCost;

    @Column(name = "RESOURCE_DESC", length = 400)
    private String resourceDesc;

}