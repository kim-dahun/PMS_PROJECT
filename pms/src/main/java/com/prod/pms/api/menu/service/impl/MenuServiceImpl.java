package com.prod.pms.api.menu.service.impl;

import com.prod.pms.api.common.service.MessageService;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.menu.service.MenuService;
import com.prod.pms.api.menu.vo.MenuListVo;
import com.prod.pms.api.menu.vo.MenuModifyVo;
import com.prod.pms.constants.CommonConstants;
import com.prod.pms.constants.HttpStatusConstants;
import com.prod.pms.constants.MessageConstants;
import com.prod.pms.domain.menu.entity.MenuList;
import com.prod.pms.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.prod.pms.constants.CommonConstants.*;
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
            MenuListVo menuListVo = getNodeMenu();
            List<MenuList> menuLists = menuRepository.findAll();
            List<MenuListVo> nodeMenuListVos = getTreeNodeList(menuLists, menuListVo.getMenuNo());
            menuListVo.setChildren(getTreeMenuList(menuLists,nodeMenuListVos));
            cmnResponseVo.setResultData(menuListVo);
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }

    }

    public MenuListVo getNodeMenu(){
        return new MenuListVo(-1L,"node","",null,null);
    }


    public List<MenuListVo> getTreeNodeList(List<MenuList> menuLists, Long parentNo){
        return menuLists.stream().filter(x->
            x.getMenuParentNo().equals(parentNo)
        ).map(MenuListVo::fromEntity).toList();
    }

    public List<MenuListVo> getTreeMenuList(List<MenuList> menuList, List<MenuListVo> nodeMenuList){
        List<MenuListVo> menuListVos = new LinkedList<>();
        for(MenuListVo menuListVo : nodeMenuList){
            List<MenuListVo> childMenuList = getTreeNodeList(menuList, menuListVo.getMenuNo());
            if(!childMenuList.isEmpty()){
                getTreeMenuList(menuList,childMenuList);
            }
            menuListVo.setChildren(childMenuList);
            menuListVos.add(menuListVo);
        }
        return menuListVos;
    }


    public ResponseEntity<CmnResponseVo> api_modifyMenuList(List<MenuModifyVo> menuModifyVos){
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        Map<String, Object> resultMap = Map.of("totalCnt",menuModifyVos.size());
        try {
            int successCnt = 0;
            for(MenuModifyVo menuModifyVo : menuModifyVos){
                if(api_modifyMenuList(menuModifyVo)){
                    successCnt++;
                }
            }
            resultMap.put("successCnt",successCnt);
            cmnResponseVo.setResultData(resultMap);
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public boolean api_modifyMenuList(MenuModifyVo menuModifyVo) {

        try {
            String crudFlag = menuModifyVo.getCrudFlag();
            MenuList menuList = null;

            switch (crudFlag){
                case CREATE -> {
                    menuList = menuModifyVo.toEntity();
                    menuRepository.save(menuList);
                }
                case UPDATE -> {
                    menuList = menuRepository.findById(menuModifyVo.getMenuNo()).orElseThrow();
                    menuList.updateMenuList(menuModifyVo.getMenuName(), menuModifyVo.getMenuUrl(), menuModifyVo.getRequestId(), menuModifyVo.getMenuParentNo());
                    menuRepository.save(menuList);
                }
                case DELETE -> {
                    menuList = menuRepository.findById(menuModifyVo.getMenuNo()).orElseThrow();
                    menuRepository.delete(menuList);
                }
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
