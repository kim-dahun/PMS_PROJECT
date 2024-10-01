package com.prod.pms.api.common.service.impl;

import com.prod.pms.api.common.service.MessageService;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.constants.HttpStatusConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.prod.pms.constants.HttpStatusConstants.*;
import static com.prod.pms.constants.MessageConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseServiceImpl implements ResponseService {

    private MessageService messageService;

    @Override
    public CmnResponseVo getLoginFail(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setStatusCode(HttpStatusConstants.INTENAL_SERVER_ERROR);
        cmnResponseVo.setMessage(messageService.getMessage(langCode,null,FAIL_LOGIN));
        cmnResponseVo.setResultData(null);
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getCreateUserSuccess() {
        return CmnResponseVo.builder()
                .message(messageService.getMessage(KO,null,CREATE_ACCOUNT_SUCCESS))
                .statusCode(HttpStatusConstants.OK)
//                .resultData(userInfo)
                .build();
    }

    @Override
    public CmnResponseVo getCreateUserFail() {
        return CmnResponseVo.builder()
                .message(messageService.getMessage(KO,null,CREATE_ACCOUNT_FAIL))
                .statusCode(HttpStatusConstants.INTENAL_SERVER_ERROR)
                .resultData(null)
                .build();
    }

    @Override
    public CmnResponseVo getNotAccessUserId(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setStatusCode(HttpStatusConstants.FORBIDDEN);
        cmnResponseVo.setMessage(messageService.getMessage(langCode,null,FAIL_ACCESS_ACCOUNT));
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getLoginSuccess(String langCode) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        cmnResponseVo.setStatusCode(HttpStatusConstants.OK);
        cmnResponseVo.setMessage(messageService.getMessage(langCode,null,LOGIN_SUCCESS));
        return cmnResponseVo;
    }

    @Override
    public CmnResponseVo getNotExistUserId() {
        return CmnResponseVo.builder()
                .message(messageService.getMessage(KO,null,NOT_EXIST_ACCOUNT))
                .statusCode(NO_CONTENT)
                .build();
    }


    @Override
    public CmnResponseVo getSearchSuccess(){
        return CmnResponseVo.builder()
                .message(messageService.getMessage(KO,null, SEARCH_SUCCESS))
                .statusCode(OK)
                .build();
    }

    @Override
    public CmnResponseVo getSearchFail(){
        return CmnResponseVo.builder()
                .message(messageService.getMessage(KO,null, SEARCH_FAIL))
                .statusCode(NO_CONTENT)
                .build();
    }

}
