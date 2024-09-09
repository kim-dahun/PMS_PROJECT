package com.prod.pms.domain.menu.entity.id;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MenuRoleAuthId implements Serializable {

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "MENU_NO")
    private Long menuNo;

}
