package com.prod.pms.domain.resource.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "RESOURCE_HIST")
public class ResourceHist extends CmnBaseCUDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HIST_SEQ", nullable = false)
    private Integer id;

    @Column(name = "COMPANY_ID", length = 40)
    private String companyId;

    @Column(name = "RESOURCE_CODE", length = 40)
    private String resourceCode;

    @Column(name = "PROJECT_CODE", length = 40)
    private String projectCode;

    @Column(name = "RESOURCE_COST")
    private Long resourceCost;

    @Column(name = "INPUT_START_DATE")
    private Instant inputStartDate;

    @Column(name = "INPUT_END_DATE")
    private Instant inputEndDate;

}