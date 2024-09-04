package com.prod.pms.domain.user.repository;

import com.prod.pms.domain.user.entity.UserCert;
import com.prod.pms.domain.user.entity.id.UserCertId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCertRepository extends JpaRepository<UserCert,UserCertId> {
}
