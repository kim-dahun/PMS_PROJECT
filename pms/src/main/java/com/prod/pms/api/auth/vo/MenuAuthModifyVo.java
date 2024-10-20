package com.prod.pms.api.auth.vo;

import com.prod.pms.api.common.vo.CmnRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuAuthModifyVo extends CmnRequestVo {

    String userId;

    String roleId;

    List<MenuAuthVo> menuAuthVos;

}
