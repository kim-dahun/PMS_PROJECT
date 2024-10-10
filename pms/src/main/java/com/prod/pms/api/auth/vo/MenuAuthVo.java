package com.prod.pms.api.auth.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuAuthVo {

    private Long menuNo;

    private String userId;

    private String createFlag;

    private String updateFlag;

    private String deleteFlag;

    private String excelFlag;

    private String readFlag;

    private List<MenuAuthVo> menuAuthChildren;

}
