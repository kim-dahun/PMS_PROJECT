package com.prod.pms.api.resource.service.impl;

import com.prod.pms.api.column.vo.ColumnModifyVo;
import com.prod.pms.api.common.service.ResponseService;
import com.prod.pms.api.common.service.TokenService;
import com.prod.pms.api.common.vo.CmnResponseVo;
import com.prod.pms.api.resource.service.ResourceService;
import com.prod.pms.api.resource.vo.ResourceModifyVo;
import com.prod.pms.api.resource.vo.ResourceReadVo;
import com.prod.pms.domain.resource.entity.ResourceManage;
import com.prod.pms.domain.resource.repository.ResoureManageRepository;
import com.prod.pms.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final TokenService jwtTokenService;
    private final ResoureManageRepository resoureManageRepository;
    private final ResponseService responseService;

    @Override
    public ResponseEntity<CmnResponseVo> api_getResourceList(ResourceReadVo resourceReadVo) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();
        try {
            List<ResourceModifyVo> resultList = getResourceList(resourceReadVo);
            cmnResponseVo.setCmnResponse(responseService.getSearchSuccess());
            cmnResponseVo.setResultData(resultList);
        }catch (Exception e){
            cmnResponseVo.setCmnResponse(responseService.getSearchFail());
        }

    }

    @Override
    public List<ResourceModifyVo> getResourceList(ResourceReadVo resourceReadVo) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withMatcher("resourceDesc", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("resourceType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("resourceName", ExampleMatcher.GenericPropertyMatchers.exact());

        return resoureManageRepository.findAll(Example.of(ResourceManage
                .builder().resourceDesc(resourceReadVo.getResourceDesc())
                .resourceName(resourceReadVo.getResourceName())
                .resourceType(resourceReadVo.getResourceType())
                .build(),exampleMatcher
        )).stream().map(ResourceModifyVo::fromEntity).toList();
    }

    @Override
    public boolean createResoure(ResourceModifyVo resourceModifyVo) {
        try {
            resourceModifyVo.setRequestId(jwtTokenService.getUserIdByToken());
            resoureManageRepository.save(resourceModifyVo.toEntity());
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateResource(ResourceModifyVo resourceModifyVo) {
        try {
            resourceModifyVo.setRequestId(jwtTokenService.getUserIdByToken());
            resoureManageRepository.save(resourceModifyVo.toEntity());
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<CmnResponseVo> api_modifyResource(List<ResourceModifyVo> resourceModifyVos) {
        CmnResponseVo cmnResponseVo = new CmnResponseVo();

        try {
            int successCnt = 0;
            for(ResourceModifyVo resourceModifyVo : resourceModifyVos){
                if(updateResource(resourceModifyVo)){
                    successCnt++;
                }
            }
            Map<String, Object> resultMap = Map.of("totalCnt",resourceModifyVos.size(),"successCnt",successCnt);
            cmnResponseVo.setResultData(resultMap);
            cmnResponseVo.setCmnResponse(successCnt==resourceModifyVos.size() ? responseService.getModifySuccess() : responseService.getModifyPartiallySucceed());
            return ResponseEntity.ok(cmnResponseVo);
        } catch(Exception e){
            cmnResponseVo.setCmnResponse(responseService.getModifyFailed());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmnResponseVo);
        }
    }
}
