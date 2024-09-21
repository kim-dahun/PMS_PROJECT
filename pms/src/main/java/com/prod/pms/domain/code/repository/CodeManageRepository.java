package com.prod.pms.domain.code.repository;

import com.prod.pms.domain.code.entity.CodeManage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeManageRepository extends JpaRepository<CodeManage, String> {
}
