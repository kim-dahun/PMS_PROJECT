package com.prod.pms.api.auth.vo;

import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRoleAuthReadVo {

    private String roleId;
    private String userId;
    private List<String> roleIds;
    private Long menuNo;

    public MenuRoleAuth toEntity(){
        return MenuRoleAuth.builder()
                .menuNo(menuNo)
                .roleId(roleId)
                .build();
    }

}
