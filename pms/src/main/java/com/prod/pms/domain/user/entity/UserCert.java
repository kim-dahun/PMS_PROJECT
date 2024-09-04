package com.prod.pms.domain.user.entity;

import com.prod.pms.constants.MessageConstants;
import com.prod.pms.domain.user.entity.id.UserCertId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER_CERT")
@IdClass(UserCertId.class)
public class UserCert {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGIN_SEQ")
    private Long userCertId;

    @Column(name = "USER_ID", length = 40)
    private String userId;

    @Column(name = "USER_CERT_KEY", length = 200)
    private String userCertKey;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;




}
