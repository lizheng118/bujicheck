package com.lizhenghome.bujicheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class BujicheckServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		
		List<String> list = new ArrayList<String>();  
		list.add( "first" );  
		list.add( "second" );
		JSONArray jsonArray = JSONArray.fromObject( list );  
		resp.getWriter().println( jsonArray );  

	}
}
