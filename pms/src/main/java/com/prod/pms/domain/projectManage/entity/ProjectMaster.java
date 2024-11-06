package com.prod.pms.domain.projectManage.entity;

import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import com.prod.pms.domain.projectManage.entity.id.ProjectMasterId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Entity
@Table(name = "PROJECT_MANAGE")
@IdClass(ProjectMasterId.class)
public class ProjectMaster extends CmnBaseCUDEntity {

    @Id
    private String companyId;

    @Id
    private String projectCd;

    @Column(name = "PROJECT_NAME", length = 80)
    private String projectName;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

}
