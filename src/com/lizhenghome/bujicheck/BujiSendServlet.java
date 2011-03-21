package com.lizhenghome.bujicheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lizhenghome.bujicheck.common.PMF;
import com.lizhenghome.bujicheck.jdo.BujiInfo;

@SuppressWarnings("serial")
public class BujiSendServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line + "\n");
			line = reader.readLine();
		}
		reader.close();
		String data = sb.toString();
//		
		//String data = "{phoneNumber=\"08033491218\", bujiStatus=\"0\", position=\"35.793962,139.791897\"}";
		JSONObject jsonObject = JSONObject.fromObject( data );
		BujiInfo bujiInfo = (BujiInfo)JSONObject.toBean(jsonObject, BujiInfo.class);
		bujiInfo.setSendDate(new Date());
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		try {
			pm.makePersistent(bujiInfo);
		} finally {
			pm.close();
		}
			
		resp.setContentType("application/json");
		resp.getWriter().println(JSONObject.fromObject(bujiInfo));  
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		this.doGet(req, resp);
	}
}
