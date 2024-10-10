package com.prod.pms.domain.menu.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "MENU_LIST")
@Entity
public class MenuList extends CmnBaseCUDEntity {

    @Id
    @Column(name = "MENU_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long menuNo;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "MENU_URL")
    private String menuUrl;

    @Column(name = "MENU_PARENT_NO")
    private Long menuParentNo;

    public void updateMenuList(String menuName, String menuUrl, String updateId, Long menuParentNo) {
        this.updateUser = updateId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuParentNo = menuParentNo;
    }

}
