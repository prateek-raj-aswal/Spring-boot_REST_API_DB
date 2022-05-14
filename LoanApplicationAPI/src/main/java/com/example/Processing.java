package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Map.Entry;


import javax.sql.DataSource;

import org.apache.camel.Exchange;


public class Processing {
	
	private DataSource dataSource; 
	   
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public void getListFromDB(Exchange exchange) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement getState = null;
		connection = dataSource.getConnection();
		getState = connection.prepareStatement("select * from borrower_application_details");
		rs = getState.executeQuery();
		List<Map<String,String>>resultlist = new ArrayList<Map<String,String>>();
		Map<String,String>result = new HashMap<>();
		while (rs.next()) {
			System.out.println("\n Name from DB "+rs.getString("name"));
			result.put("appid",rs.getString("appid"));
			result.put("id",rs.getString("id"));
			result.put("Name",rs.getString("name"));  
			result.put("dob",rs.getString("dob")); 
			result.put("age",rs.getString("age"));  
			result.put("pan",rs.getString("pan"));   
			result.put("loanamtreq",rs.getString("loanamtreq")); 
			result.put("mthlyincome",rs.getString("mthlyincome"));
			result.put("nationality",rs.getString("nationality")); 
			result.put("execempid",rs.getString("execempid")); 
			result.put("officeempid",rs.getString("officeempid")); 
			result.put("appdate",rs.getString("appdate")); 
			result.put("eligibility",rs.getString("eligibility")); 
			resultlist.add(result);
	        }
	        rs.close();    
		exchange.getIn().setBody(resultlist);
	}
}
