package com.prod.pms.domain.user.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserCertId implements Serializable {

    @Column(name = "LOGIN_SEQ")
    private Long userCertId;

}
