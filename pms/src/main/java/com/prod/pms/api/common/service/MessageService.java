package com.prod.pms.api.common.service;

import java.util.Locale;

public interface MessageService {

    String getMessage(String langCode, Object[] msgParams , String msgCode);

}
