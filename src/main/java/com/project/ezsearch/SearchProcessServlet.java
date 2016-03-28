package com.project.ezsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.ezsearch.jsonclasses.Business;
import com.project.ezsearch.jsonclasses.SearchResponse;
import com.google.gson.Gson;
import com.project.ezsearch.geojsonClasses.Feature;
import com.project.ezsearch.geojsonClasses.Geometry;
import com.project.ezsearch.geojsonClasses.Properties;
import com.project.ezsearch.geojsonClasses.ResultGeojson;

public class SearchProcessServlet extends HttpServlet {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   static final String DB_URL = "jdbc:mysql://127.6.134.2:3306/ezsearch_db";

	   //  Database credentials
	   static final String USER = "adminstqHYBl";
	   static final String PASS = "string";


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String searchterm = request.getParameter("searchterm");
		String location = request.getParameter("location");
		String limit = request.getParameter("limit");
		String name = null;
		if (limit.isEmpty()) {
			limit = "5";
		}

		String searchResponse = YelpAPI.getSearchResults(searchterm, location, limit);
		Gson gson = new Gson();

		SearchResponse searchResponseObj = gson.fromJson(searchResponse, SearchResponse.class);

		ResultGeojson resultGeojson = new ResultGeojson();
		resultGeojson.setType("FeatureCollection");
		List<Feature> features = new ArrayList<Feature>();
		List<Object> mapBoundList = new ArrayList<Object>();
		int count = 1;
		for (Business business : searchResponseObj.getBusinesses()) {

			Feature feature = new Feature();
			feature.setType("Feature");

			Properties properties = new Properties();
			properties.setName(business.getName());
			properties.setPopupContent(Integer.toString(count) + ". " + business.getName());
			feature.setProperties(properties);

			Geometry geometry = new Geometry();
			geometry.setType("Point");
			List<Double> cordinates = new ArrayList<Double>();
			cordinates.add(business.getLocation().getCoordinate().getLongitude());
			cordinates.add(business.getLocation().getCoordinate().getLatitude());
			geometry.setCoordinates(cordinates);
			feature.setGeometry(geometry);

			features.add(feature);

			List<Double> mapBound = new ArrayList<Double>();
			mapBound.add(business.getLocation().getCoordinate().getLatitude());
			mapBound.add(business.getLocation().getCoordinate().getLongitude());
			mapBoundList.add(mapBound);
			count++;
		}
		resultGeojson.setFeatures(features);

// ***********


		   Connection conn = null;
		   Statement stmt = null;
	      try {
			Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM test";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         name  = rs.getString("NAME");
		         int age = rs.getInt("AGE");

		         //Display values
		         System.out.print("ID: " + name);
		         System.out.print(", Age: " + age);

		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}




// **********

		String geojson = gson.toJson(resultGeojson);

		RequestDispatcher dispatcher = request.getRequestDispatcher("searchresult.jsp");
		request.setAttribute("resultJson", searchResponseObj); // set your
																// String value
																// in the
																// attribute
		request.setAttribute("resultGeojson", geojson);
		request.setAttribute("mapBoundList", mapBoundList);
		if(name == null) {
			name = "default";
		}
		request.setAttribute("namefromdb", name);
		dispatcher.forward(request, response);

	}

	// // Redirect POST request to GET request.
	// @Override
	// public void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws IOException, ServletException {
	// doGet(request, response);
	// }

	// Filter the string for special HTML characters to prevent
	// command injection attack
	private static String htmlFilter(String message) {
		if (message == null)
			return null;
		int len = message.length();
		StringBuffer result = new StringBuffer(len + 20);
		char aChar;

		for (int i = 0; i < len; ++i) {
			aChar = message.charAt(i);
			switch (aChar) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(aChar);
			}
		}
		return (result.toString());
	}

}
