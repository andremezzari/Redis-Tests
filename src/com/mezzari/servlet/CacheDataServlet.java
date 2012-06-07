package com.mezzari.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.mezzari.util.RedisUtils;

public class CacheDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TIMEOUT = 30;

	public CacheDataServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Jedis jedis = RedisUtils.getConnection();

		try {
			jedis.setex(String.valueOf(System.currentTimeMillis()), TIMEOUT,
					request.getParameter("name"));
		} finally {
			RedisUtils.releaseConnection(jedis);
		}

		response.sendRedirect("list");
	}
}