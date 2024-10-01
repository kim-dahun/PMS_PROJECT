package com.prod.pms.api.menu.service.impl;

import com.prod.pms.api.common.service.MessageService;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.menu.service.MenuService;
import com.prod.pms.api.menu.vo.MenuListVo;
import com.prod.pms.api.menu.vo.MenuModifyVo;
import com.prod.pms.constants.HttpStatusConstants;
import com.prod.pms.constants.MessageConstants;
import com.prod.pms.domain.menu.entity.MenuList;
import com.prod.pms.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.prod.pms.constants.HttpStatusConstants.OK;
import static com.prod.pms.constants.MessageConstants.KO;
import static com.prod.pms.constants.MessageConstants.SEARCH_SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {


    private MessageService messageService;
    private ResponseService responseService;
    private MenuRepository menuRepository;
    @Override
    public ResponseEntity<CmnResponseVo> api_getMenuList(MenuListVo menuListVo) {

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            List<MenuList> menuList = menuRepository.findAllByMenuNameLikeIgnoreCase(menuListVo.getMenuName());
            cmnResponseVo.setResultData(menuList);
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getMenuListAll() {

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            List<MenuList> menuList = menuRepository.findAll();
            cmnResponseVo.setResultData(menuList);
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyMenuList(MenuModifyVo menuModifyVo) {

        CmnResponseVo cmnResponseVo = new CmnResponseVo();

        return null;
    }
}
