package com.prod.pms.api.user.service.impl;

import com.prod.pms.api.common.service.MessageService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.common.vo.JwtTokenVo;
import com.prod.pms.api.user.service.UserService;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.api.user.vo.UserLoginVo;
import com.prod.pms.constants.HttpStatusConstants;
import com.prod.pms.constants.JwtConstants;
import com.prod.pms.constants.MessageConstants;
import com.prod.pms.domain.user.entity.UserInfo;
import com.prod.pms.domain.user.repository.UserInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
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

import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_ACCESS;
import static com.prod.pms.constants.JwtConstants.TOKEN_TYPE_REFRESH;
import static com.prod.pms.constants.MessageConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("NO EXIST USER"));
    }

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

    public ResponseEntity<CmnResponseVo> getUserInfoApi(UserLoginVo userLoginVo, HttpServletRequest request){

        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            UserInfo userInfo = getUserInfo(userLoginVo.getUserId(), userLoginVo.getUserPassword());
            if(userInfo==null || userInfo.getUserId()==null){
                cmnResponseVo.setMessage(messageService.getMessage(request,null,NOT_EXIST_ACCOUNT));
                cmnResponseVo.setStatusCode(HttpStatusConstants.NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cmnResponseVo);
            } else if(!userInfo.isEnabled()){
                cmnResponseVo.setStatusCode(HttpStatusConstants.FORBIDDEN);
                cmnResponseVo.setMessage(messageService.getMessage(request,null,FAIL_ACCESS_ACCOUNT));
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(cmnResponseVo);
            }

            cmnResponseVo.setResultData(UserInfoVo.fromEntity(userInfo));
            cmnResponseVo.setStatusCode(HttpStatusConstants.OK);
            cmnResponseVo.setMessage(messageService.getMessage(request,null,LOGIN_SUCCESS));

            JwtTokenVo jwtTokenVo = tokenService.getRefreshToken(userInfo);

            ResponseCookie accessCookie = ResponseCookie.from(TOKEN_TYPE_ACCESS, jwtTokenVo.getAccessToken()).httpOnly(true).maxAge(60*10).path("/").build();

            ResponseCookie refreshCookie = ResponseCookie.from(TOKEN_TYPE_REFRESH, jwtTokenVo.getRefreshToken()).httpOnly(true).path("/")
                    .maxAge(24 * 60 * 60).build();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.SET_COOKIE,accessCookie.toString());
            httpHeaders.add(HttpHeaders.SET_COOKIE,refreshCookie.toString());


            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setStatusCode(HttpStatusConstants.INTENAL_SERVER_ERROR);
            cmnResponseVo.setMessage(messageService.getMessage(request,null,FAIL_LOGIN));
            cmnResponseVo.setResultData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }


    }


}
