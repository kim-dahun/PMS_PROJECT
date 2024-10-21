package com.prod.pms.domain.column.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "MENU_COLUMN")
@Entity
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "COLUMN_ID")
    private Long columnId;

    @jakarta.persistence.Column(name = "MENU_NO", length = 40)
    private Long menuNo;

    @jakarta.persistence.Column(name = "VIEW_ID", length = 40)
    private String viewId;

    @jakarta.persistence.Column(name = "REQUIRE_FLAG", length = 1)
    private String requireFlag; // 필수값 여부

    @jakarta.persistence.Column(name = "EDIT_FLAG", length = 1)
    private String editFlag; // 수정 가능여부 - 쓰기 가능, 수정 불가

    @jakarta.persistence.Column(name = "READONLY", length = 1)
    private String readonly; // 읽기전용 여부 - 쓰기, 수정 불가

    @jakarta.persistence.Column(name = "COLUMN_NAME", length = 200)
    private String columnName; // DB에서 관리되는 물리적 이름

    @jakarta.persistence.Column(name = "COLUMN_TEXT", length = 200)
    private String columnText; // 표시명

    @jakarta.persistence.Column(name = "COLUMN_TYPE", length = 40)
    private String colummType; // 숫자, 문자, 드롭다운, 데이트타임

    @jakarta.persistence.Column(name = "SELECT_ITEM_CODE", length = 40)
    private String selectItemCode; // 드랍다운일 경우 사용할 아이템 목록 코드그룹 ID

}
