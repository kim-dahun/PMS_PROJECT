package com.prod.pms.domain.user.entity;

import com.prod.pms.constants.Role;
import com.prod.pms.domain.common.entity.CmnBaseCUDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "USER_LIST")
@Entity
public class UserInfo extends CmnBaseCUDEntity implements UserDetails {

    @Id
    @Column(name = "USER_ID", length = 40, updatable = false)
    private String userId;

    @Column(name = "COMPANY_ID", length = 40)
    private String companyId;

    @Column(name = "USER_PASSWORD", length = 200)
    private String userPassword;

    @Column(name = "USER_NAME", length = 40)
    private String userKorName;

    @Column(name = "USER_BIRTH", length = 40)
    private String userBirth;

    @Column(name = "USER_EMAIL", length = 200)
    private String userEmail;

    @Column(name = "USER_PHONE", length = 40)
    private String userPhone;

    @Column(name = "USE_FLAG")
    private String useFlag;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_ID")
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param userEmail
     * @param userPhone
     * @param userBirth
     * @param useFlag
     * @param userPassword
     * @param userKorName
     * @param roles
     */
    public void updateUserInfo(String userEmail, String userPhone, String userBirth, String useFlag, String userPassword, String userKorName, List<Role> roles, String updateId){
        this.userEmail = userEmail==null ?  this.userEmail : userEmail;
        this.userPhone = userPhone==null ? this.userPhone : userPhone;
        this.userBirth = userBirth==null ? this.userBirth : userBirth;
        this.userKorName = userKorName ==null ? this.userKorName : userKorName;
        this.useFlag = useFlag == null ? this.useFlag : useFlag;
        this.userPassword = userPassword == null ? this.userPassword : userPassword;
        this.roles = roles == null ? this.roles : roles;
        this.updateUser = updateId == null ? this.updateUser : updateId;
    }


    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.useFlag.equalsIgnoreCase("N");
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.useFlag.equalsIgnoreCase("N");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.useFlag.equalsIgnoreCase("N");
    }

    @Override
    public boolean isEnabled() {
        return !this.useFlag.equalsIgnoreCase("N");
    }
}
