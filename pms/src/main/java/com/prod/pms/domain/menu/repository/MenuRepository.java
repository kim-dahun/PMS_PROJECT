package com.prod.pms.domain.menu.repository;

import com.prod.pms.domain.menu.entity.MenuList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuList, Long> {
}
