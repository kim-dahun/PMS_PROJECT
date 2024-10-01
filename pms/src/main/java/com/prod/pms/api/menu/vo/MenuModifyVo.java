package com.prod.pms.api.menu.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuModifyVo extends CmnRequestVo {

    String menuName;

    String menuUrl;

    String updateUser;

}
