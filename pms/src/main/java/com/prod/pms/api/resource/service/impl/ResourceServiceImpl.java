package com.prod.pms.api.resource.service.impl;

import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.resource.service.ResourceService;
import com.prod.pms.api.resource.vo.ResourceModifyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {


    @Override
    public ResponseEntity<CmnResponseVo> api_getResourceList() {
        return null;
    }

    @Override
    public List<ResourceModifyVo> getResourceList() {
        return null;
    }

    @Override
    public boolean createResoure(ResourceModifyVo resourceModifyVo) {
        return false;
    }

    @Override
    public boolean updateResource(ResourceModifyVo resourceModifyVo) {
        return false;
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyResource(List<ResourceModifyVo> resourceModifyVos) {
        return null;
    }
}
