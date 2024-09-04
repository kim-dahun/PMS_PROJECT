package com.prod.pms.api.common.service.impl;

import ch.qos.logback.core.util.Loader;
import com.prod.pms.api.common.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageSource messageSource;

    @Override
    public String getMessage(String langCode, Object[] msgParams ,String msgCode) {
        Locale locale = new Locale(langCode);
        return messageSource.getMessage(msgCode,msgParams, locale);
    }
}
