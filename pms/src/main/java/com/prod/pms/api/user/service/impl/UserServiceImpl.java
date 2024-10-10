package com.prod.pms.api.user.service.impl;

import com.prod.pms.api.common.service.MessageService;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.common.vo.JwtTokenVo;
import com.prod.pms.api.user.service.UserService;
import com.prod.pms.api.user.vo.UserInfoModifyVo;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.api.user.vo.UserLoginVo;
import com.prod.pms.constants.*;
import com.prod.pms.domain.user.entity.UserInfo;
import com.prod.pms.domain.user.repository.UserInfoRepository;
import com.prod.pms.utils.JwtTokenUtils;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.prod.pms.constants.CommonConstants.CREATE;
import static com.prod.pms.constants.CommonConstants.UPDATE;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_ACCESS;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_REFRESH;
import static com.prod.pms.constants.MessageConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;
    private final TokenService tokenService;
    private final EntityManager em;
    private final ResponseService responseService;

    public UserInfo getUserInfo(String username, String userPassword) {

        try {
            UserInfo userInfo = userInfoRepository.findById(username).orElseThrow();
            if (passwordEncoder.matches(userPassword, userInfo.getPassword())) {
                return userInfo;
            }
            return new UserInfo();
        } catch (UsernameNotFoundException notFoundException) {
            return new UserInfo();
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<CmnResponseVo> isAvailableUser(UserInfo userInfo){
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        if(userInfo==null || userInfo.getUserId()==null){
            cmnResponseVo.setCmnResponse(responseService.getNotExistUserId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cmnResponseVo);
        } else if(!userInfo.isEnabled()){
            cmnResponseVo.setCmnResponse(responseService.getNotAccessUserId(KO));
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(cmnResponseVo);
        }
        return null;
    }

    public ResponseEntity<CmnResponseVo> getUserInfoApi(UserLoginVo userLoginVo, HttpServletRequest request){

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            UserInfo userInfo = getUserInfo(userLoginVo.getUserId(), userLoginVo.getUserPassword());
            ResponseEntity<CmnResponseVo> notFoundResult = isAvailableUser(userInfo);
            if(notFoundResult==null){
                return notFoundResult;
            }

            cmnResponseVo.setCmnResponse(responseService.getLoginSuccess(KO));
            cmnResponseVo.setResultData(UserInfoVo.fromEntity(userInfo));
            HttpHeaders httpHeaders = getTokenHeaders(userInfo);
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getLoginFail(KO));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }


    }

    public HttpHeaders getTokenHeaders(UserInfo userInfo){
        JwtTokenVo jwtTokenVo = tokenService.getRefreshToken(userInfo);

        ResponseCookie accessCookie = ResponseCookie.from(TOKEN_TYPE_ACCESS, jwtTokenVo.getAccessToken()).httpOnly(true).maxAge(60*10).path("/").build();

        ResponseCookie refreshCookie = ResponseCookie.from(TOKEN_TYPE_REFRESH, jwtTokenVo.getRefreshToken()).httpOnly(true).path("/")
                .maxAge(24 * 60 * 60).build();

        HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.add(org.springframework.http.HttpHeaders.SET_COOKIE,accessCookie.toString());
        httpHeaders.add(org.springframework.http.HttpHeaders.SET_COOKIE,refreshCookie.toString());
        return httpHeaders;

    }

    public String getEncodedPassword(String password){
        if(password!=null){
            return passwordEncoder.encode(password);
        }
        return null;
    }


    public List<Role> getOrDefaultRoles(UserInfoModifyVo userInfoModifyVo, List<Role> roleList) {

        if(userInfoModifyVo.getUserRole()!=null && !roleList.contains(Role.valueOf(userInfoModifyVo.getUserRole()))){
            roleList.add(Role.valueOf(userInfoModifyVo.getUserRole()));
        }
        return roleList;
    }

    @Override
    public ResponseEntity<CmnResponseVo> modifyUserInfo(UserInfoModifyVo userInfoModifyVo) {

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        if(userInfoModifyVo.getUserId() == null){

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cmnResponseVo);
        }

        try {
            UserInfo userInfo = null;
            switch (userInfoModifyVo.getCrudFlag()){
                case CREATE -> {
                    userInfo = userInfoModifyVo.toEntity();
                    userInfo.getRoles().add(Role.USER);
                }
                case UPDATE -> {
                    userInfo = userInfoRepository.findById(userInfoModifyVo.getUserId()).orElseThrow();
                    String encodePassword = getEncodedPassword(userInfoModifyVo.getPassword());
                    List<Role> roleList = getOrDefaultRoles(userInfoModifyVo, userInfo.getRoles());
                    userInfo.updateUserInfo(userInfoModifyVo.getEmail(), userInfoModifyVo.getPhone(), userInfoModifyVo.getBirth(), userInfoModifyVo.getUseFlag(), encodePassword, userInfoModifyVo.getName(), roleList, tokenService.getUserIdByToken());

                }
            }

            if(userInfo == null){
                throw new Exception();
            }

            userInfoRepository.save(userInfo);
            cmnResponseVo.setCmnResponse(responseService.getCreateUserSuccess());
//            cmnResponseVo.setResultData(userInfo);


            return ResponseEntity.ok(cmnResponseVo);

        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getCreateUserFail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }


    }
}
