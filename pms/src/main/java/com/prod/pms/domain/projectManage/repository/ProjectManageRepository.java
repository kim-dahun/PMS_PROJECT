package com.prod.pms.domain.projectManage.repository;

import com.prod.pms.domain.projectManage.entity.ProjectMaster;
import com.prod.pms.domain.projectManage.entity.id.ProjectMasterId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectManageRepository extends JpaRepository<ProjectMaster, ProjectMasterId> {
}
