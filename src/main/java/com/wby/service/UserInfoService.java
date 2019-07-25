package com.wby.service;

import com.wby.model.UserInfo;

public interface UserInfoService {

	/**
	 * 通过UID查询User
	 * @param string
	 * @return
	 */
	UserInfo getUserByUid(String string);

	/**
	 * 添加用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	UserInfo insertUser(String userName, String pwd);

}
