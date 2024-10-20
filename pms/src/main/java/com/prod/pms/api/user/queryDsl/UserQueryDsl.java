package com.prod.pms.api.user.queryDsl;

import com.prod.pms.api.user.vo.UserInfoVo;
import com.prod.pms.domain.user.entity.QUserInfo;
import com.prod.pms.domain.user.entity.UserInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.prod.pms.domain.user.entity.QUserInfo.*;

@RequiredArgsConstructor
@Service
public class UserQueryDsl {

    private final JPAQueryFactory queryFactory;


    public UserInfoVo getUserSimpleInfoById(UserInfoVo userInfoVo){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String userId = userInfoVo.getUserId();

        if(StringUtils.hasText(userId)){
            booleanBuilder.and(eqUserId(userId));
        }

        try {
            return queryFactory.select(Projections.bean(UserInfoVo.class, userInfo.userId,
                            userInfo.roles.as("userAuthority"),
                            userInfo.companyId,
                            userInfo.userKorName.as("userName"),
                            userInfo.useFlag)
                    ).from(userInfo)
                    .where(booleanBuilder)
                    .fetchOne();
        } catch (Exception e){
            return null;
        }



    }

    public BooleanExpression eqUserId(String userId){
        return userInfo.userId.eq(userId);
    }





}
