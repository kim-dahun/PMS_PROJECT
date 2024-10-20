package com.prod.pms.api.auth.queryDsl;

import com.prod.pms.api.auth.vo.MenuAuthDataVo;
import com.prod.pms.api.auth.vo.MenuAuthReadVo;
import com.prod.pms.api.auth.vo.MenuAuthVo;
import com.prod.pms.api.auth.vo.MenuRoleAuthReadVo;
import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.constants.Role;
import com.prod.pms.domain.menu.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.xml.transform.Transformer;
import java.util.LinkedList;
import java.util.List;

import static com.prod.pms.domain.menu.entity.QMenuAuth.*;
import static com.prod.pms.domain.menu.entity.QMenuList.menuList;
import static com.prod.pms.domain.menu.entity.QMenuRoleAuth.*;

@Service
@RequiredArgsConstructor
public class MenuAuthQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    private final String menuAuthList = "SELECT\n" +
            "    m.MENU_NO as menuNo,\n" +
            "    m.MENU_NAME as menuName,\n" +
            "    m.MENU_PARENT_NO as menuParentNo,\n" +
            "    IF(SUM(IF(m.CREATE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as createFlag,\n" +
            "    IF(SUM(IF(m.UPDATE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as updateFlag,\n" +
            "    IF(SUM(IF(m.DELETE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as deleteFlag,\n" +
            "    IF(SUM(IF(m.EXCEL_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N')  as excelFlag, \n" +
            "    IF(SUM(IF(m.READ_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N')   as readFlag, \n" +
            "FROM (SELECT\n" +
            "    m.MENU_NO,\n" +
            "    m.MENU_NAME,\n" +
            "    m.MENU_PARENT_NO,\n" +
            "    mu.CREATE_FLAG,\n" +
            "    mu.UPDATE_FLAG,\n" +
            "    mu.DELETE_FLAG,\n" +
            "    mu.EXCEL_FLAG,\n" +
            "    mu.READ_FLAG\n" +
            "FROM MENU_LIST m\n" +
            "LEFT JOIN MENU_AUTH_LIST mu\n" +
            "ON mu.MENU_NO = m.MENU_NO\n" +
            "WHERE mu.USER_ID = :userId " +
            "UNION ALL\n" +
            "SELECT\n" +
            "    m.MENU_NO,\n" +
            "    m.MENU_NAME,\n" +
            "    m.MENU_PARENT_NO,\n" +
            "    mr.CREATE_FLAG,\n" +
            "    mr.UPDATE_FLAG,\n" +
            "    mr.DELETE_FLAG,\n" +
            "    mr.EXCEL_FLAG,\n" +
            "    mr.READ_FLAG\n" +
            "FROM MENU_LIST m\n" +
            "LEFT JOIN MENU_ROLE_AUTH_LIST mr\n" +
            "ON mr.MENU_NO = m.MENU_NO\n" +
            "WHERE " +
            "mr.ROLE_ID IN (\n" +
            "    SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID = :userId \n" +
            "    )) m\n" +
            "GROUP BY m.MENU_NO, m.MENU_NAME, m.MENU_PARENT_NO";


    private final String menuAuthOne = "SELECT\n" +
            "    m.MENU_NO as menuNo,\n" +
            "    m.MENU_NAME as menuName,\n" +
            "    m.MENU_PARENT_NO as menuParentNo,\n" +
            "    IF(SUM(IF(m.CREATE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as createFlag,\n" +
            "    IF(SUM(IF(m.UPDATE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as updateFlag,\n" +
            "    IF(SUM(IF(m.DELETE_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N') as deleteFlag,\n" +
            "    IF(SUM(IF(m.EXCEL_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N')  as excelFlag, \n" +
            "    IF(SUM(IF(m.READ_FLAG = 'Y', 1, 0)) > 0, 'Y', 'N')   as readFlag, \n" +
            "FROM (SELECT\n" +
            "    m.MENU_NO,\n" +
            "    m.MENU_NAME,\n" +
            "    m.MENU_PARENT_NO,\n" +
            "    mu.CREATE_FLAG,\n" +
            "    mu.UPDATE_FLAG,\n" +
            "    mu.DELETE_FLAG,\n" +
            "    mu.EXCEL_FLAG,\n" +
            "    mu.READ_FLAG\n" +
            "FROM MENU_LIST m\n" +
            "LEFT JOIN MENU_AUTH_LIST mu\n" +
            "ON mu.MENU_NO = m.MENU_NO\n" +
            "WHERE mu.USER_ID = :userId " +
            "AND m.MENU_NO = :menuNo \n" +
            "UNION ALL\n" +
            "SELECT\n" +
            "    m.MENU_NO,\n" +
            "    m.MENU_NAME,\n" +
            "    m.MENU_PARENT_NO,\n" +
            "    mr.CREATE_FLAG,\n" +
            "    mr.UPDATE_FLAG,\n" +
            "    mr.DELETE_FLAG,\n" +
            "    mr.EXCEL_FLAG,\n" +
            "    mr.READ_FLAG\n" +
            "FROM MENU_LIST m\n" +
            "LEFT JOIN MENU_ROLE_AUTH_LIST mr\n" +
            "ON mr.MENU_NO = m.MENU_NO\n" +
            "WHERE " +
            "m.MENU_NO = :menuNo \n" +
            "AND mr.ROLE_ID IN (\n" +
            "    SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID = :userId \n" +
            "    )) m\n" +
            "GROUP BY m.MENU_NO, m.MENU_NAME, m.MENU_PARENT_NO";


    public JPAQuery<MenuAuthVo> getMenuAuthList(MenuAuthReadVo menuAuthReadVo){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String userId = menuAuthReadVo.getUserId();
        Long menuNo = menuAuthReadVo.getMenuNo();

        if(StringUtils.hasText(userId)){
            booleanBuilder.and(eqUserId(userId));
        }

        if(menuNo!=null){
            booleanBuilder.and(eqMenuNo(menuNo));
        }


        return jpaQueryFactory.select(Projections.bean(
                MenuAuthVo.class,
                menuList.menuNo,
                        menuList.menuName,
                        menuList.menuParentNo,
                menuAuth.userId,
                menuAuth.createFlag.nullif("N"),
                menuAuth.updateFlag.nullif("N"),
                menuAuth.deleteFlag.nullif("N"),
                menuAuth.readFlag.nullif("N"),
                menuAuth.excelFlag.nullif("N")

        )).from(menuList)
                .leftJoin(menuAuth)
                .on(menuList.menuNo.eq(menuAuth.menuNo))
                .where(booleanBuilder);

    }

    private BooleanExpression eqUserId(String userId) {
        return menuAuth.userId.eq(userId);
    }

    private BooleanExpression eqMenuNo(Long menuNo) {
        return menuAuth.menuNo.eq(menuNo);
    }


    public JPAQuery<MenuAuthVo> getMenuRoleAuthList(MenuRoleAuthReadVo menuRoleAuthReadVo){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        List<String> userRoleId = menuRoleAuthReadVo.getRoleIds();
        String userId = menuRoleAuthReadVo.getUserId();
        Long menuNo = menuRoleAuthReadVo.getMenuNo();

        if(userRoleId != null && !userRoleId.isEmpty()){
            booleanBuilder.and(eqUserRoleId(userRoleId));
        }

        if(menuNo!=null){
            booleanBuilder.and(eqMenuNo(menuNo));
        }

        return jpaQueryFactory.select(Projections.bean(MenuAuthVo.class,
                menuList.menuNo,
                        menuList.menuName,
                        menuList.menuParentNo,
                        menuRoleAuth.roleId.as("userRoleId"),
                        menuRoleAuth.createFlag.nullif("N"),
                        menuRoleAuth.updateFlag.nullif("N"),
                        menuRoleAuth.deleteFlag.nullif("N"),
                        menuRoleAuth.readFlag.nullif("N"),
                        menuRoleAuth.excelFlag.nullif("N")

        )).from(menuList)
                .leftJoin(menuRoleAuth)
                .on(menuList.menuNo.eq(menuRoleAuth.menuNo))
                .where(booleanBuilder);

    }

    public BooleanExpression eqUserRoleId(List<String> userRoleId){
        return menuRoleAuth.roleId.in(userRoleId);
    }

    public List<MenuAuthDataVo> getMenuAuthListByIdAndRoleIds(MenuRoleAuthReadVo menuAuthReadVo){

        String userId = menuAuthReadVo.getUserId();

        List<MenuAuthDataVo> menuAuthVos = (List<MenuAuthDataVo>) em.createNativeQuery(menuAuthList).setParameter("userId",userId)
                        .getResultList();

        return menuAuthVos;
    }

    public MenuAuthDataVo getMenuAuthByIdAndRoleIds(MenuRoleAuthReadVo menuAuthReadVo){

        String userId = menuAuthReadVo.getUserId();
        Long menuNo = menuAuthReadVo.getMenuNo();
        try {
            MenuAuthDataVo menuAuthVo = ((List<MenuAuthDataVo>) em.createNativeQuery(menuAuthOne).setParameter("userId",userId).setParameter("menuNo",menuNo)
                    .getResultList()).getFirst();

            return menuAuthVo;
        } catch (Exception e){
            return null;
        }

    }


}
