package com.prod.pms.domain.menu.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUEntity;
import com.prod.pms.domain.menu.entity.id.MenuAuthId;
import com.prod.pms.domain.menu.entity.id.MenuRoleAuthId;
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
@Table(name = "MENU_ROLE_AUTH_LIST")
@Entity
@IdClass(MenuRoleAuthId.class)
public class MenuRoleAuth extends CmnBaseCUEntity {

    @Id
    private Long menuNo;

    @Id
    private String roleId;

    @Column(name = "CREATE_FLAG", length = 1)
    private String createFlag;

    @Column(name = "UPDATE_FLAG", length = 1)
    private String updateFlag;

    @Column(name = "DELETE_FLAG", length = 1)
    private String deleteFlag;

    @Column(name = "EXCEL_FLAG", length = 1)
    private String excelFlag;

    @Column(name = "READ_FLAG", length = 1)
    private String readFlag;


}
