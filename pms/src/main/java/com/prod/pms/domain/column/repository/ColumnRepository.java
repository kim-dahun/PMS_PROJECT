package com.prod.pms.domain.column.repository;

import com.prod.pms.domain.column.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column, Long> {
}
