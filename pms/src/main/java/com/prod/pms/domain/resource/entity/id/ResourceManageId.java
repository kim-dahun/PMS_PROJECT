package com.prod.pms.domain.resource.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResourceManageId implements Serializable {
    private static final long serialVersionUID = -5730937990122537229L;
    @Column(name = "COMPANY_ID", nullable = false, length = 40)
    private String companyId;

    @Column(name = "RESOURCE_CODE", nullable = false, length = 40)
    private String resourceCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResourceManageId entity = (ResourceManageId) o;
        return Objects.equals(this.companyId, entity.companyId) &&
                Objects.equals(this.resourceCode, entity.resourceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, resourceCode);
    }

}