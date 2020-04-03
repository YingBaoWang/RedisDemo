package com.wby.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wby.service.ConsumerService;
import com.wby.service.ProducerService;

@Controller
@RequestMapping("/Jms")
public class JmsController {

	//队列消息生产者
	@Autowired(required = true)
	private ProducerService producerService;
	
	//队列消息消费者
	@Autowired
	private ConsumerService consumerService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
	
	/**
	 *发送消息 
	 */
	@RequestMapping(value="SendMessage",method = RequestMethod.POST)
	@ResponseBody
	public void send(HttpServletRequest request){
		String type = request.getParameter("type");//1-queue 2-topic
		String isDefault = request.getParameter("isDefault");//0否1是
		String msg = sdf.format(new Date());
		
		System.out.println(Thread.currentThread().getName()+"------------send to jms Start");
        producerService.sendMessage(msg,type,isDefault);
        System.out.println(Thread.currentThread().getName()+"------------send to jms End");
    }

	/**
	 * 接受消息
	 */
    @RequestMapping(value= "/ReceiveMessage",method = RequestMethod.POST)
    @ResponseBody
    public void receive(HttpServletRequest request){
    	String type = request.getParameter("type");//1-queue 2-topic
    	String isDefault = request.getParameter("isDefault");//0否1是
    	
        System.out.println(Thread.currentThread().getName()+"------------receive from jms Start");
        consumerService.receive(type,isDefault);
        System.out.println(Thread.currentThread().getName()+"------------receive from jms End");
    }
}
