package com.wby.demo;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class JedisDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JedisShardInfo shardInfo = new JedisShardInfo("redis://127.0.0.1:6379");//���������ӵı��ص�ַ�Ͷ˿�
        shardInfo.setPassword("redis");//����������
		//���ӱ��ص� Redis ����
		Jedis jedis = new Jedis(shardInfo);
		System.out.println("���ӳɹ�");
		
		//�鿴�����Ƿ�����
        System.out.println("������������: "+jedis.ping());
        
        //���� redis �ַ�������
        /*jedis.set("runoobkey", "www.runoob.com");
        // ��ȡ�洢�����ݲ����
        System.out.println("redis �洢���ַ���Ϊ: "+ jedis.get("runoobkey"));
        */
        
        /*jedis.del("runoobkey");
        System.out.println("redis key-runoobkey ɾ��");
        */
        // ��ȡ���ݲ����
        Set<String> keys = jedis.keys("*"); 
        Iterator<String> it=keys.iterator() ;   
        while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
        
        jedis.close();
	}

}
