package com.mezzari.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.mezzari.util.RedisUtils;

public class ListDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListDataServlet() {
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
			PrintWriter writer = response.getWriter();
			Set<String> keys = jedis.keys("*");
			
			writer.write("<h1>Cached Data</h1>");
			writer.write("<a href=\"list\">refresh</a>&nbsp;&nbsp;");
			writer.write("<a href=\"index.html\">back</a><br><br>");
			for (String key : keys) {
				writer.write("<b>Key: </b>");
				writer.write(key);
				writer.write("<br>");
				writer.write("<b>Value: </b>");
				writer.write(jedis.get(key));
				writer.write("<hr>");
			}
		} finally {
			RedisUtils.releaseConnection(jedis);
		}
	}
}