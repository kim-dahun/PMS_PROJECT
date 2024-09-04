package com.prod.pms.domain.user.repository;

import com.prod.pms.domain.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
