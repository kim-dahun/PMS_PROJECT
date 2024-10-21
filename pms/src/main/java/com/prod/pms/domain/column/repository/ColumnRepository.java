package com.prod.pms.domain.column.repository;

import com.prod.pms.domain.column.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Long> {

    List<Column> findByMenuNoAndViewId(Long menuNo, String viewId);

}
