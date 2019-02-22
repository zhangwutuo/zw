package com.gzg.sysTest.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class JedisCache {

    @Autowired
    private JedisPool jedisPool;

    public String set(){
        return jedisPool.getResource().set("jedis1","JEDIS");
    }

    public String get(){
        return jedisPool.getResource().get("jedis1");
    }
}
