package com.prod.pms.api.auth.service;

import com.prod.pms.api.auth.vo.MenuAuthReadVo;
import com.prod.pms.api.auth.vo.MenuAuthVo;
import com.prod.pms.domain.menu.entity.MenuAuth;

import java.util.List;

public interface MenuAuthService {

    List<MenuAuth> api_getMenuAuthList(MenuAuthReadVo menuAuthReadVo);

    MenuAuthVo api_getMenuAuthById(MenuAuthReadVo menuAuthReadVo);

}
