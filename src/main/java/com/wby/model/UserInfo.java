package com.wby.model;

import java.io.Serializable;

public class UserInfo implements Serializable{
    /**
	 * 使用jedis 需要序列化接口
	 */
	private static final long serialVersionUID = 1L;

	private String uid;

    private String username;

    private String pwd;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", pwd=" + pwd + "]";
	}
  
}