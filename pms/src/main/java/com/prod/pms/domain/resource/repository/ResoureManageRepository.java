package com.prod.pms.domain.resource.repository;

import com.prod.pms.domain.resource.entity.ResourceManage;
import com.prod.pms.domain.resource.entity.id.ResourceManageId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResoureManageRepository extends JpaRepository<ResourceManage, ResourceManageId> {
}
