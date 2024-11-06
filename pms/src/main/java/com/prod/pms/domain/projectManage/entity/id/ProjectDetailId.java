package com.prod.pms.domain.projectManage.entity.id;

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
public class ProjectDetailId implements Serializable {
    private static final long serialVersionUID = -5730937912122537229L;
    @Column(name = "COMPANY_ID", length = 40)
    private String companyId;

    @Column(name = "PROJECT_CODE", length = 40)
    private String projectCd;

    @Column(name = "SUB_JOB_CODE", length = 40)
    private String subJobCode;

}
