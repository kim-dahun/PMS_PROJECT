package com.prod.pms.api.auth.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.domain.menu.entity.MenuAuth;
import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MenuAuthVo extends CmnRequestVo {

    private Long menuNo;
    private String menuName;
    private Long menuParentNo;
    private String userId;

    private String userRoleId;

    private String createFlag;

    private String updateFlag;

    private String deleteFlag;

    private String excelFlag;

    private String readFlag;

    private List<MenuAuthVo> menuAuthChildren;

    public static MenuAuthVo fromVO(MenuAuthDataVo menuAuthDataVo){
        return MenuAuthVo.builder()
                .menuNo(menuAuthDataVo.getMenuNo())
                .menuName(menuAuthDataVo.getMenuName())
                .menuParentNo(menuAuthDataVo.getMenuParentNo())
                .createFlag(menuAuthDataVo.getCreateFlag())
                .updateFlag(menuAuthDataVo.getUpdateFlag())
                .deleteFlag(menuAuthDataVo.getDeleteFlag())
                .excelFlag(menuAuthDataVo.getExcelFlag())
                .readFlag(menuAuthDataVo.getReadFlag())
                .build();
    }

    public MenuAuth toMenuAuth(String createUser, String updateUser){

        return MenuAuth.builder()
                .userId(userId)
                .menuNo(menuNo)
                .createFlag(createFlag)
                .updateFlag(updateFlag)
                .deleteFlag(deleteFlag)
                .excelFlag(excelFlag)
                .readFlag(readFlag)
                .createUser(createUser)
                .updateUser(updateUser)
                .build();

    }

    public MenuRoleAuth toMenuRoleAuth(String createUser, String updateUser){

        return MenuRoleAuth.builder()
                .roleId(userRoleId)
                .menuNo(menuNo)
                .createFlag(createFlag)
                .updateFlag(updateFlag)
                .deleteFlag(deleteFlag)
                .excelFlag(excelFlag)
                .readFlag(readFlag)
                .createUser(createUser)
                .updateUser(updateUser)
                .build();

    }


}
