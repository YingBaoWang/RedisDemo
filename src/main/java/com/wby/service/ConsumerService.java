package com.wby.service;

public interface ConsumerService {
	/**
	 * 接受消息
	 * @param type 1-queue 2-topic
	 * @param isDefault 是否默认的队伍|话题 （0否1是）
	 */
	void receive(String type, String isDefault);

}
