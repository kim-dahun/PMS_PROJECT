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
public class MenuAuthId implements Serializable {

    @Column(name = "MENU_NO")
    private Long menuNo;

    @Column(name = "USER_ID")
    private String userId;

}
