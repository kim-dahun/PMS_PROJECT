package com.prod.pms.api.menu.controller;

import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.menu.service.MenuService;
import com.prod.pms.api.menu.vo.MenuListVo;
import com.prod.pms.api.menu.vo.MenuModifyVo;
import com.prod.pms.constants.ApiConstants;
import com.prod.pms.domain.menu.entity.MenuList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(ApiConstants.API_URL + "/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/select")
    public ResponseEntity<CmnResponseVo> api_getMenu(MenuListVo menuListVo){
        return menuService.api_getMenuList(menuListVo);
    }

    @GetMapping("/select/all")
    public ResponseEntity<CmnResponseVo> api_getMenuAll(){
        return menuService.api_getMenuListAll();
    }

    @PostMapping("/create")
    public ResponseEntity<CmnResponseVo> api_createMenuList(@RequestBody List<MenuModifyVo> menuModifyVos){
        return menuService.api_modifyMenuList(menuModifyVos);
    }

    @PutMapping("/update")
    public ResponseEntity<CmnResponseVo> api_updateMenuList(@RequestBody List<MenuModifyVo> menuModifyVos){
        return menuService.api_modifyMenuList(menuModifyVos);
    }

    @PutMapping("/delete")
    public ResponseEntity<CmnResponseVo> api_deleteMenuList(@RequestBody List<MenuModifyVo> menuModifyVos){
        return menuService.api_modifyMenuList(menuModifyVos);
    }

}
