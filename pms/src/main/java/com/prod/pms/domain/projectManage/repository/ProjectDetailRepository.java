package com.prod.pms.domain.projectManage.repository;

import com.prod.pms.domain.projectManage.entity.ProjectDetail;
import com.prod.pms.domain.projectManage.entity.id.ProjectDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDetailRepository extends JpaRepository<ProjectDetail, ProjectDetailId> {
}
