package com.prod.pms.api.menu.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.menu.entity.MenuList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuModifyVo extends CmnRequestVo {

    Long menuNo;

    String menuName;

    String menuUrl;

    Long menuParentNo;



    public MenuList toEntity(){
        return MenuList.builder()
                .menuNo(menuNo)
                .menuName(menuName)
                .menuUrl(menuUrl)
                .menuParentNo(menuParentNo)
                .updateUser(this.getRequestId())
                .createUser(this.getRequestId())
                .build();
    }


}
