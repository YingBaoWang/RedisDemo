package com.wby.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.wby.service.ProducerService;

/**
 * 生产者
 * @author Administrator
 *
 */
@Service("producerService")
//@Component
public class ProducerServiceImpl implements ProducerService{

	@Resource(name = "queueDestination")
	private Destination queueDestination;
	
	@Resource(name = "topicDestination")
	private Destination topicDestination;
	
	
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;
	
	/**
	 * 向指定队列发送消息
	 */
	@Override
	public void sendMessage(final String msg,String type,boolean isDefault) {
		// TODO Auto-generated method stub
		Destination destination = queueDestination;
		JmsTemplate jmsTemplate = new JmsTemplate();
		//type:1-queue 2-topic
		if ("1".equalsIgnoreCase(type)) {
			jmsTemplate = jmsQueueTemplate;
			destination = true == isDefault?jmsTemplate.getDefaultDestination():queueDestination;
		}else if ("2".equalsIgnoreCase(type)) {
			jmsTemplate = jmsTopicTemplate;
			destination = true == isDefault?jmsTemplate.getDefaultDestination():topicDestination;
		}
		send(destination, msg, jmsTemplate);
	}
	private void send(Destination destination,final String msg,JmsTemplate jmsTemplate){
		System.out.println(">>>>\n"+Thread.currentThread().getName()+"向" + destination.toString() + "发送了消息------------" + msg);
		
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				TextMessage textMessage = session.createTextMessage(msg);
				textMessage.setJMSReplyTo(destination);
				return textMessage;
			}
        });
	}
}
