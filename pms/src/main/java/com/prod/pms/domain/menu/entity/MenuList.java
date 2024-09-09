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
@Builder
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
}