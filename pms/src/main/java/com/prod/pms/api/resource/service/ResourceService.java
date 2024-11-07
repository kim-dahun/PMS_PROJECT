package com.prod.pms.api.resource.service;

import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.resource.vo.ResourceModifyVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceService {

    public ResponseEntity<CmnResponseVo> api_getResourceList();

    public List<ResourceModifyVo> getResourceList();

    public boolean createResoure(ResourceModifyVo resourceModifyVo);

    public boolean updateResource(ResourceModifyVo resourceModifyVo);

    public ResponseEntity<CmnResponseVo> api_modifyResource(List<ResourceModifyVo> resourceModifyVos);

}
