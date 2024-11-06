package com.prod.pms.domain.company.repository;

import com.prod.pms.domain.company.entity.CompanyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyList, String> {
}
