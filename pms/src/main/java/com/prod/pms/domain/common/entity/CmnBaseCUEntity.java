package com.prod.pms.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@Setter
public abstract class CmnBaseCUEntity {

    @CreatedBy
    @Column(name = "CREATE_USER", updatable = false)
    protected String createUser;

    @CreatedDate
    @Column(name = "CREATE_DATE", updatable = false)
    protected LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    protected LocalDateTime updateDate;

    @LastModifiedBy
    @Column(name = "UPDATE_USER")
    protected String updateUser;


}
