package com.lizhenghome.bujicheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lizhenghome.bujicheck.common.PMF;
import com.lizhenghome.bujicheck.jdo.BujiInfo;

@SuppressWarnings("serial")
public class BujicheckServlet extends HttpServlet {
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
		
		
		//String data = "{phoneNumber=\"08033491218\", startDate=\"2011-03-19\", endDate=\"2011-03-20\"}";
		
		JSONObject jsonObject = JSONObject.fromObject( data );

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		Query query = pm.newQuery(BujiInfo.class);
		query.setFilter("phoneNumber == phoneNumberParam");
		query.declareParameters("String phoneNumberParam");
		query.setOrdering("sendDate desc");
//		query.declareImports("import java.util.Date");
//		query.setFilter("phoneNumber == phoneNumberParam && sendDate >= startDateParam && sendDate < endDateParam");
//		query.declareParameters("String phoneNumberParam, Date startDateParam, Date endDateParam");
		
		String phoneNumberParam = jsonObject.getString("phoneNumber");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
//			Date startDateParam = df.parse(jsonObject.getString("startDate"));
//			Date endDateParam = df.parse(jsonObject.getString("endDate"));
			
			@SuppressWarnings("unchecked")
//			List<BujiInfo> searchResult = (List<BujiInfo>)query.execute(phoneNumberParam, startDateParam, endDateParam);
			List<BujiInfo> searchResult = (List<BujiInfo>)query.execute(phoneNumberParam);
			JSONArray jsonArray = JSONArray.fromObject(searchResult);
			
			resp.setContentType("application/json");
			resp.getWriter().println(JSONArray.fromObject(jsonArray));  
		} catch (Exception e) {
			
		} finally {
			pm.close();
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		this.doGet(req, resp);
	}
}
