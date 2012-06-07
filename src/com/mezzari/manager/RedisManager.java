package com.mezzari.manager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
	private static final RedisManager instance = new RedisManager();
	private static JedisPool pool;

	private RedisManager() {
	}

	public final static RedisManager getInstance() {
		return instance;
	}

	public void connect() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxActive(200);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setMaxIdle(50);
		poolConfig.setMinIdle(1);
		poolConfig.setMaxWait(3000);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setNumTestsPerEvictionRun(10);
		poolConfig.setTimeBetweenEvictionRunsMillis(60000);
		pool = new JedisPool(poolConfig, "localhost", 6379);
	}

	public void release() {
		pool.destroy();
	}

	public Jedis getJedis() {
		return pool.getResource();
	}

	public void returnJedis(Jedis jedis) {
		pool.returnResource(jedis);
	}
}