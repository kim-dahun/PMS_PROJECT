package com.prod.pms.api.auth.service;

import com.prod.pms.api.auth.vo.MenuAuthModifyVo;
import com.prod.pms.api.auth.vo.MenuAuthReadVo;
import com.prod.pms.api.auth.vo.MenuAuthVo;
import com.prod.pms.api.auth.vo.MenuRoleAuthReadVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.domain.menu.entity.MenuAuth;
import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuAuthService {

    List<MenuAuth> getMenuAuthList(MenuAuthReadVo menuAuthReadVo);

    List<MenuRoleAuth> getMenuRoleAuthList(MenuRoleAuthReadVo menuRoleAuthReadVo);

    ResponseEntity<CmnResponseVo> api_getMenuAuthByMenuNoAndUser(MenuRoleAuthReadVo menuAuthReadVo);

    ResponseEntity<CmnResponseVo> api_getMenuAuthByUser(MenuRoleAuthReadVo menuAuthReadVo);

    ResponseEntity<CmnResponseVo> api_getMenuAuthByRole(MenuRoleAuthReadVo menuAuthReadVo);

    ResponseEntity<CmnResponseVo> api_modifyMenuAuth(List<MenuAuthVo> menuAuthVo);

    ResponseEntity<CmnResponseVo> api_modifyRoleMenuAuth(List<MenuAuthVo> menuAuthVo);




}
