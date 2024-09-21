package com.prod.pms.domain.code.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CODE_MANAGE")
@Entity
public class CodeManage extends CmnBaseCUDEntity {

    @Id
    @Column(name = "CODE_ID", length = 40)
    private String codeId;

    @Column(name = "CODE_NAME", length = 40)
    private String codeName;

    @Column(name = "TEXT_ATTR01", length = 400)
    private String textAttr01;

    @Column(name = "TEXT_ATTR02", length = 400)
    private String textAttr02;

    @Column(name = "NUM_ATTR01")
    private Double numAttr01;

    @Column(name = "NUM_ATTR02")
    private Double numAttr02;

    @Column(name = "CODE_TYPE", length = 40)
    private String codeType;

    @Column(name = "USE_FLAG", length = 1)
    private String useFlag;

    @Column(name = "COMPANY_ID", length = 80)
    private String companyId;

}
