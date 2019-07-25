package com.wby.dao;

import com.wby.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}