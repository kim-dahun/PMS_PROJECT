package com.prod.pms.domain.menu.repository;

import com.prod.pms.domain.menu.entity.MenuAuth;
import com.prod.pms.domain.menu.entity.id.MenuAuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuAuthRepository extends JpaRepository<MenuAuth, MenuAuthId> {
}
