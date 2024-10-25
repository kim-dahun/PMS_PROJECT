package com.prod.pms.api.auth.service.impl;

import com.prod.pms.api.auth.queryDsl.MenuAuthQueryDsl;
import com.prod.pms.api.auth.service.MenuAuthService;
import com.prod.pms.api.auth.vo.MenuAuthModifyVo;
import com.prod.pms.api.auth.vo.MenuAuthReadVo;
import com.prod.pms.api.auth.vo.MenuAuthVo;
import com.prod.pms.api.auth.vo.MenuRoleAuthReadVo;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.user.service.UserService;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.constants.CommonConstants;
import com.prod.pms.domain.menu.entity.MenuAuth;
import com.prod.pms.domain.menu.entity.MenuList;
import com.prod.pms.domain.menu.entity.MenuRoleAuth;
import com.prod.pms.domain.menu.entity.id.MenuAuthId;
import com.prod.pms.domain.menu.entity.id.MenuRoleAuthId;
import com.prod.pms.domain.menu.repository.MenuAuthRepository;
import com.prod.pms.domain.menu.repository.MenuRoleAuthRepository;
import com.prod.pms.domain.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.prod.pms.constants.CommonConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeunAuthServiceImpl implements MenuAuthService {

    private MenuAuthRepository menuAuthRepository; // 개인 권한
    private MenuRoleAuthRepository menuRoleAuthRepository; // 그룹 권한

    private UserService userService;
    private TokenService tokenService;
    private ResponseService responseService;
    private MenuAuthQueryDsl menuAuthQueryDsl;

    @Override
    public List<MenuAuth> getMenuAuthList(MenuAuthReadVo menuAuthReadVo) {
        return menuAuthRepository.findAll(Example.of(menuAuthReadVo.toEntity()));
    }

    @Override
    public List<MenuRoleAuth> getMenuRoleAuthList(MenuRoleAuthReadVo menuRoleAuthReadVo) {
        return menuRoleAuthRepository.findAll(Example.of(menuRoleAuthReadVo.toEntity()));
    }

    public List<MenuAuthVo> getConcatAuthList(MenuRoleAuthReadVo menuRoleAuthReadVo){
        return menuAuthQueryDsl.getMenuAuthListByIdAndRoleIds(menuRoleAuthReadVo).stream().map(MenuAuthVo::fromVO).toList();
    }

    public MenuAuthVo getMenuAuthByIdAndRole(MenuRoleAuthReadVo menuRoleAuthReadVo){
        return MenuAuthVo.fromVO(menuAuthQueryDsl.getMenuAuthByIdAndRoleIds(menuRoleAuthReadVo));
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getMenuAuthByMenuNoAndUser(MenuRoleAuthReadVo menuAuthReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getMenuAuthByIdAndRole(
                    menuAuthReadVo
            ));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getMenuAuthByUser(MenuRoleAuthReadVo menuAuthReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getConcatAuthList(
                    menuAuthReadVo
            ));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_getMenuAuthByRole(MenuRoleAuthReadVo menuAuthReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            cmnResponseVo.setResultData(getMenuRoleAuthList(
                    menuAuthReadVo
            ));
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            return ResponseEntity.ok(cmnResponseVo);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyMenuAuth(List<MenuAuthVo> menuAuthModifyVos) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            int successCount = 0;
            for(MenuAuthVo menuAuthVo : menuAuthModifyVos){
                if(modify(menuAuthVo)){
                    successCount++;
                }
            }

            if(successCount == menuAuthModifyVos.size()){
                cmnResponseVo.setCmnResponse(responseService.getModifySuccess());
            } else {
                cmnResponseVo.setCmnResponse(responseService.getModifyPartiallySucceed());
            }
            return ResponseEntity.ok(cmnResponseVo);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyRoleMenuAuth(List<MenuAuthVo> menuAuthModifyVos) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            int successCount = 0;
            for(MenuAuthVo menuAuthVo : menuAuthModifyVos){
                if(modifyRoleAuth(menuAuthVo)){
                    successCount++;
                }
            }

            if(successCount == menuAuthModifyVos.size()){
                cmnResponseVo.setCmnResponse(responseService.getModifySuccess());
            } else {
                cmnResponseVo.setCmnResponse(responseService.getModifyPartiallySucceed());
            }
            return ResponseEntity.ok(cmnResponseVo);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }

    public boolean modify(MenuAuthVo menuAuthVo){
        try {
            switch (menuAuthVo.getCrudFlag()){
                case CREATE ->
                        menuAuthRepository.save(menuAuthVo.toMenuAuth(tokenService.getUserIdByToken(), tokenService.getUserIdByToken()));
                case UPDATE ->
                {
                    menuAuthRepository.save(menuAuthVo.toMenuAuth(tokenService.getUserIdByToken(), tokenService.getUserIdByToken()));
                }
                case DELETE ->
                {
                    menuAuthRepository.deleteById(MenuAuthId.builder().menuNo(menuAuthVo.getMenuNo()).userId(menuAuthVo.getUserId()).build());
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean modifyRoleAuth(MenuAuthVo menuAuthVo){
        try {
            switch (menuAuthVo.getCrudFlag()){
                case CREATE ->
                        menuRoleAuthRepository.save(menuAuthVo.toMenuRoleAuth(tokenService.getUserIdByToken(), tokenService.getUserIdByToken()));
                case UPDATE ->
                {
                    menuRoleAuthRepository.save(menuAuthVo.toMenuRoleAuth(tokenService.getUserIdByToken(), tokenService.getUserIdByToken()));
                }
                case DELETE ->
                {
                    menuRoleAuthRepository.deleteById(MenuRoleAuthId.builder().menuNo(menuAuthVo.getMenuNo()).roleId(menuAuthVo.getUserRoleId()).build());
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // 그룹 권한을 베이스로 개인 권한을 추가로 가짐.



}
