package com.wby.utils;

import org.springframework.util.ErrorHandler;

public class JmsErrorHandler implements ErrorHandler{

	@Override
	public void handleError(Throwable arg0) {
		// TODO Auto-generated method stub
		System.out.println("出错啦！");
		arg0.printStackTrace();
	}

}
