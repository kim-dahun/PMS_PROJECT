package com.prod.pms.domain.menu.repository;

import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import com.prod.pms.domain.menu.entity.id.MenuRoleAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRoleAuthRepository extends JpaRepository<MenuRoleAuth, MenuRoleAuthId> {
}
