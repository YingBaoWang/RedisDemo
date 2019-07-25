package com.wby.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static String ADDR = "127.0.0.1";
    private static int PORT = 6379;
    private static String AUTH = "root";
    
    //private static int MAX_ACTIVE = 1024;
    
    private static int MAX_IDLE = 200;
    
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 10000;
    
    private static boolean TEST_ON_BORROW = true;
    
    // 被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
 	private static volatile JedisPool jedisPool = null;
    
   // 私有化静态方法，不能new
 	private JedisPoolUtil() {};
 	
    static {
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized static Jedis getJedis(){
        try{
            if(jedisPool != null){
                Jedis jedis = jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void returnResource(final Jedis jedis){
        if(jedis != null){
            //jedisPool.returnResource(jedis);
        }
    }
}