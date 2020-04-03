package com.wby.service;

public interface ProducerService {
	/**
	 * 发送消息
	 * @param msg
	 * @param type 1-queue 2-topic
	 * @param isDefault 是否默认的队伍|话题 （0否1是）
	 */
	void sendMessage(final String msg,String type, String isDefault);
}
