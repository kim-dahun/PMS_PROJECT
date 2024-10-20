package com.prod.pms.api.common.service;

import com.prod.pms.api.common.vo.CmnRequestVo;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.constants.HttpStatusConstants;

import static com.prod.pms.constants.MessageConstants.CREATE_ACCOUNT_FAIL;
import static com.prod.pms.constants.MessageConstants.KO;

public interface ResponseService {

    CmnResponseVo getNotExistUserId();

    CmnResponseVo getLoginFail(String langCode);

    CmnResponseVo getCreateUserSuccess();

    CmnResponseVo getCreateUserFail();

    CmnResponseVo getNotAccessUserId(String langCode);

    CmnResponseVo getLoginSuccess(String langCode);

    CmnResponseVo getSearchSuccess();

    CmnResponseVo getSearchFail();

    CmnResponseVo getModifySuccess();

    CmnResponseVo getModifyFailed();

    CmnResponseVo getModifyPartiallySucceed();

}
