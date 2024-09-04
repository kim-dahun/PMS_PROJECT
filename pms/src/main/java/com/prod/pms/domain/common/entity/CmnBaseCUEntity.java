package com.prod.pms.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@EntityListeners(AuditingEntityListener.class)
public abstract class CmnBaseCUEntity {

    @CreatedBy
    @Column(name = "CREATE_USER", updatable = false)
    private String createUser;

    @CreatedDate
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @LastModifiedBy
    @Column(name = "UPDATE_USER")
    private String updateUser;


}
