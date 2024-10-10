package com.prod.pms.domain.column.entity;


import com.prod.pms.domain.common.entity.CmnBaseCUEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MENU_COLUMN_SEQ")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class ColumnManage extends CmnBaseCUEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLUMN_MANAGE_ID")
    private Long columnManageId;

    @Column(name = "MENU_NO", length = 40)
    private String menuNo;

    @Column(name = "VIEW_ID", length = 40)
    private String viewId;

    @Column(name = "USER_TYPE", length = 40)
    private String userType; // 회사인지 개인인지

    @Column(name = "USER_ID", length = 80)
    private String userId; // USERID 혹은 COMPANYID가 들어옴

    @Column(name = "COLUMN_ID")
    private Long columnId;

    @Column(name = "COLUMN_TEXT")
    private String columnText;

    @Column(name = "COLUMN_SEQ")
    private Long columnSeq;

    @jakarta.persistence.Column(name = "EDIT_FLAG", length = 1)
    private String editFlag; // 수정 가능여부 - 쓰기 가능, 수정 불가

    @jakarta.persistence.Column(name = "READONLY", length = 1)
    private String readonly; // 읽기전용 여부 - 쓰기, 수정 불가

    @jakarta.persistence.Column(name = "REQUIRE_FLAG", length = 1)
    private String requireFlag; // 필수값 여부

    @jakarta.persistence.Column(name = "COLUMN_TYPE", length = 40)
    private String colummType; // 숫자, 문자, 드롭다운, 데이트타임

    @jakarta.persistence.Column(name = "SELECT_ITEM_CODE", length = 40)
    private String selectItemCode; // 드랍다운일 경우 사용할 아이템 목록 코드그룹 ID


}
