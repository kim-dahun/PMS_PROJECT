package com.prod.pms.api.menu.service;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.menu.vo.MenuListVo;
import com.prod.pms.api.menu.vo.MenuModifyVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuService {

    ResponseEntity<CmnResponseVo> api_getMenuList(MenuListVo menuListVo);

    ResponseEntity<CmnResponseVo> api_getMenuListAll();

    boolean api_modifyMenuList(MenuModifyVo menuModifyVo);

    ResponseEntity<CmnResponseVo> api_modifyMenuList(List<MenuModifyVo> menuModifyVos);

//    ResponseEntity<CmnResponseVo> api_


}
