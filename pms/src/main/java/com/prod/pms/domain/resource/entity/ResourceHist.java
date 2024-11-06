package com.prod.pms.domain.resource.entity;

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
public class ResourceHist {
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

    @Column(name = "INPUT_START_DATE")
    private Instant inputStartDate;

    @Column(name = "INPUT_END_DATE")
    private Instant inputEndDate;

}