package com.prod.pms.domain.menu.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Embeddable
public class MenuRoleAuthId implements Serializable {

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "MENU_NO")
    private Long menuNo;

}
