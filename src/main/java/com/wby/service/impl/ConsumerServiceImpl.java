package com.wby.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.wby.service.ConsumerService;

/**
 * 消费者
 * @author Administrator
 *
 */
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService{

	@Resource(name = "queueDestination")
	private Destination queueDestination;
	
	@Resource(name = "topicDestination")
	private Destination topicDestination;
	
	
	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	@Resource(name = "jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;
	
	@Override
	public void receive(String type, boolean isDefault) {
		// TODO Auto-generated method stub
		try {
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
			
			TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
			System.out.println("从队列"+destination.toString()+"收到了消息：\t"+textMessage.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
