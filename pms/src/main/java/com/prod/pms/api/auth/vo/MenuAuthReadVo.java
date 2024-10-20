package com.prod.pms.api.auth.vo;

import com.prod.pms.domain.menu.entity.MenuAuth;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuAuthReadVo {

    private Long menuNo;
    private String userId;

    public MenuAuth toEntity(){
        return MenuAuth.builder()
                .menuNo(menuNo)
                .userId(userId)
                .build();
    }

}
