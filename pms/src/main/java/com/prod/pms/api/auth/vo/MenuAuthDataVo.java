package com.prod.pms.api.auth.vo;

import lombok.Getter;
import lombok.Setter;

public interface MenuAuthDataVo {

    Long getMenuNo();
    String getMenuName();
    Long getMenuParentNo();

    String getCreateFlag();
    String getUpdateFlag();
    String getDeleteFlag();
    String getExcelFlag();
    String getReadFlag();

}
