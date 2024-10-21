package com.prod.pms.domain.code.repository;

import com.prod.pms.domain.code.entity.CodeManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeManageRepository extends JpaRepository<CodeManage, String> {

    List<CodeManage> findByCodeTypeAndUseFlagAndCompanyId(String codeType, String useFlag, String companyId);

}
