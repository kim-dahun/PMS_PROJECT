package com.prod.pms.domain.user.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USER_LIST")
@Entity
public class UserInfo extends CmnBaseCUDEntity {

    @Id
    @Column(name = "USER_ID", length = 40, updatable = false)
    private String userid;

    @Column(name = "COMPANY_ID", length = 40)
    private String companyId;

    @Column(name = "USER_PASSWORD", length = 40)
    private String userPassword;

    @Column(name = "USER_NAME", length = 40)
    private String userName;

    @Column(name = "USER_BIRTH", length = 40)
    private String userBirth;

    @Column(name = "USER_EMAIL", length = 200)
    private String userEmail;

    @Column(name = "USER_PHONE", length = 40)
    private String userPhone;


}
