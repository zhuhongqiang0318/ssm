package com.web.zhq.util;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author : Created by zhq 
 * @since  : 2017/7/6 
 */
@Service
public class JedisUtil {

    public static Logger logger =LoggerFactory.getLogger(JedisUtil.class);

    public static JedisPool jedisPool=null;
   /* static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(false);
        String host= "127.0.0.1";
        int port=6379;
        String passwd="";
        if(passwd!=null && passwd.length()>0){
            jedisPool = new JedisPool(config, host, port, 3000, passwd);
        }else{
            jedisPool = new JedisPool(config, host, port);
        }
    }*/

    /**
     * 获取jedis对象
     * @return
     * @throws JedisException
     */
    public Jedis getJedis() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            logger.error("获取jedis连接池error",e);
        }
        return jedis;
    }


    /**
     * 归回jedis链接
     * @param jedis
     * @param isBroken
     */
    protected void returnResource(Jedis jedis, boolean isBroken) {
        if (isBroken) {
            jedisPool.returnBrokenResource(jedis);
        } else {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 字符串添加到jedis
     * @param key
     * @param value
     * @param cacheSeconds
     * @param methodName
     * @return
     */
    public String addStringToJedis(String key, String value, int cacheSeconds, String methodName,int getDBIndex) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = this.getJedis();
            lastVal = jedis.getSet(key, value);
            if(cacheSeconds!=0){
                jedis.expire(key,cacheSeconds);
            }
        } catch (Exception e) {
            isBroken = true;
            logger.error("failed:" + methodName, key, value, e);
        } finally {
            returnResource(jedis, isBroken);
        }
        return lastVal;
    }

    /**
     *
     * @param key
     * @param
     * @return
     */
 /*   public String getStringFromJedis(String key){
        ShardedJedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = this.getJedis();
            lastVal = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            logger.error("failed:"+ key,e);
        } finally {
            returnResource(jedis, isBroken);
        }
        return lastVal;
    }

    *//**
     * 删除某db的某个key值
     * @param key
     * @return
     *//*
    public Long delKeyFromJedis(String key,int getDBIndex) {
        boolean isBroken = false;
        ShardedJedis jedis = null;
        long result = 0;
        try {
            jedis = this.getJedis();
            logger.info( "succeed:delKeyFromJedis");
            return jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            logger.info("failed:delKeyFromJedis", e);
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    *//**
     * 判断key是否存在
     * @param key
     * @param methodName
     * @param getDBIndex
     * @return
     *//*
    public boolean existKey(String key, String methodName,int getDBIndex) {
        ShardedJedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = this.getJedis();
            logger.info( "succeed:"+methodName);
            return jedis.exists(key);
        } catch (Exception e) {
            isBroken = true;
            logger.info("failed:"+methodName, e);
        } finally {
            returnResource(jedis, isBroken);
        }
        return false;
    }*/
}
