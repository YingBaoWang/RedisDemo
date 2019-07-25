package com.wby.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wby.model.UserInfo;
import com.wby.service.UserInfoService;

@Controller
@RequestMapping(value="/User")
public class UserController {
    
    @Autowired
    private UserInfoService userInfoService;
  
    @ResponseBody
	@RequestMapping(value="/QueryUser",method=RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	public String queryUser(HttpServletRequest request,HttpSession session){
    	System.out.println("断点。。。。。。。");
    	String uid = request.getParameter("uid");
		UserInfo user = userInfoService.getUserByUid(uid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        System.out.println(JSONObject.toJSONString(map));
		return JSONObject.toJSONString(map);
	}
    
    @ResponseBody
    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public void addUser(HttpServletResponse response,HttpServletRequest request) throws Exception {
        System.out.println("******addUser********");
        
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        UserInfo userInfo = userInfoService.insertUser(userName, pwd);
        if (null != userInfo) {
			System.out.println("OK");
		} else {
			System.out.println("FAIL");
		}
    }
}
