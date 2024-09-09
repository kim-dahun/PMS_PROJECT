package com.prod.pms.domain.menu.entity.id;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MenuAuthId implements Serializable {

    @Column(name = "MENU_NO")
    private Long menuNo;

    @Column(name = "USER_ID")
    private String userId;

}
