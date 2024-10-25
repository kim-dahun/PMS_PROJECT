package com.prod.pms.api.auth.controller;

import com.prod.pms.api.auth.service.MenuAuthService;
import com.prod.pms.api.auth.vo.MenuAuthModifyVo;
import com.prod.pms.api.auth.vo.MenuAuthReadVo;
import com.prod.pms.api.auth.vo.MenuAuthVo;
import com.prod.pms.api.auth.vo.MenuRoleAuthReadVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.constants.ApiConstants;
import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_URL + "/auth")
public class MenuAuthController {

    private final MenuAuthService menuAuthService;

    @GetMapping("/user/menu-one")
    public ResponseEntity<CmnResponseVo> api_getMenuAuth(MenuRoleAuthReadVo menuAuthReadVo){
        return menuAuthService.api_getMenuAuthByMenuNoAndUser(menuAuthReadVo);
    }

    @GetMapping("/user/menu-list")
    public ResponseEntity<CmnResponseVo> api_getMenuAuthList(MenuRoleAuthReadVo menuAuthReadVo){
        return menuAuthService.api_getMenuAuthByUser(menuAuthReadVo);
    }

    @PostMapping("/user/menu-list")
    public ResponseEntity<CmnResponseVo> api_modifyMenuAuth(@RequestBody List<MenuAuthVo> menuAuthModifyVos){
        return menuAuthService.api_modifyMenuAuth(menuAuthModifyVos);
    }

    @PostMapping("/role/menu-list")
    public ResponseEntity<CmnResponseVo> api_modifyMenuRoleAuth(@RequestBody List<MenuAuthVo> menuAuthModifyVos){
        return menuAuthService.api_modifyRoleMenuAuth(menuAuthModifyVos);
    }

    @GetMapping("/role/menu-list")
    public ResponseEntity<CmnResponseVo> api_getMenuRoleAuthList(MenuRoleAuthReadVo menuRoleAuthReadVo){
        return menuAuthService.api_getMenuAuthByRole(menuRoleAuthReadVo);
    }


}
