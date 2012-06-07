package com.mezzari.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mezzari.manager.RedisManager;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		RedisManager.getInstance().release();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		RedisManager.getInstance().connect();
	}
}