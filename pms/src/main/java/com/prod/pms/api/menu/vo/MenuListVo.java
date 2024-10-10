package com.prod.pms.api.menu.vo;

import com.prod.pms.domain.menu.entity.MenuList;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MenuListVo {

    Long menuNo;

    String menuName;

    String menuUrl;

    Long parentNo;

    List<MenuListVo> children;

    public static MenuListVo fromEntity(MenuList menuList){
        return MenuListVo
                .builder()
                .menuNo(menuList.getMenuNo())
                .menuName(menuList.getMenuName())
                .menuUrl(menuList.getMenuUrl())
                .parentNo(menuList.getMenuParentNo())
                .build();
    }

}
