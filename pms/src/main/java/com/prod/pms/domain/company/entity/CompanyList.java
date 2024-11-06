package com.prod.pms.domain.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COMPANY_LIST")
public class CompanyList {
    @Id
    @Column(name = "COMPANY_ID", nullable = false, length = 40)
    private String companyId;

    @Column(name = "COMPANY_NAME", length = 80)
    private String companyName;

    @Column(name = "COMPANY_ADDRESS", length = 200)
    private String companyAddress;

    @Column(name = "COMPANY_PHONE", length = 40)
    private String companyPhone;

    @Column(name = "COMPANY_FLAG", length = 1)
    private String companyFlag;

    @Column(name = "COMPANY_FILE_01", length = 200)
    private String companyFile01;

    @Column(name = "COMPANY_FILE_02", length = 200)
    private String companyFile02;

    @Column(name = "COMPANY_BUSSINESS_NO", length = 40)
    private String companyBussinessNo;

}