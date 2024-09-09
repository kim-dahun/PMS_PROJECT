package com.prod.pms.api.common.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public interface MessageService {

    String getMessage(String langCode, Object[] msgParams , String msgCode);

    String getMessage(HttpServletRequest request, Object[] msgParams, String msgCode);
}
