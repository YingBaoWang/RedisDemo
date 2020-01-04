package com.wby.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsListener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage) arg0;
		try {
			System.out.println("接收到了消息，消息内容是："+textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
