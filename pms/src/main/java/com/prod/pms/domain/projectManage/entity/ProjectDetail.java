package com.prod.pms.domain.projectManage.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import com.prod.pms.domain.projectManage.entity.id.ProjectDetailId;
import com.prod.pms.domain.projectManage.entity.id.ProjectMasterId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Entity
@Table(name = "PROJECT_DETAIL")
@IdClass(ProjectDetailId.class)
public class ProjectDetail extends CmnBaseCUDEntity {


    @Id
    private String companyId;

    @Id
    private String projectCd;

    @Id
    private String subJobCode;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "SUB_JOB_NAME", length = 80)
    private String subJobName;

    @Column(name = "SUB_JOB_DESC", length = 400)
    private String subJobDesc;


}
