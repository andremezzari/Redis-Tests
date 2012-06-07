package com.mezzari.util;

import redis.clients.jedis.Jedis;

import com.mezzari.manager.RedisManager;

public class RedisUtils {

	public static Jedis getConnection() {
		return RedisManager.getInstance().getJedis();
	}

	public static void releaseConnection(Jedis jedis) {
		RedisManager.getInstance().returnJedis(jedis);
	}
}