package com.prod.pms.domain.column.repository;

import com.prod.pms.domain.column.entity.ColumnManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnManageRepository extends JpaRepository<ColumnManage, Long> {

    List<ColumnManage> findByViewIdAndMenuNoAndUserId(String viewId, Long menuNo, String userId);

}
