import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsonclasses.SearchResponse;

import com.google.gson.Gson;


public class SearchProcessServlet extends HttpServlet{

	   @Override
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	               throws IOException, ServletException {
	      // Set the response message's MIME type
//	      response.setContentType("text/html; charset=UTF-8");
//	      // Allocate a output writer to write the response message into the network socket
//	      PrintWriter out = response.getWriter();
//
//	      // Write the response message, in an HTML page
//	      try {
//	         out.println("<!DOCTYPE html>");
//	         out.println("<html><head>");
//	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
//	         out.println("<title>Echo Servlet</title></head>");
//	         out.println("<body><h2>You have entered below things::</h2>");
//
//
//	         String searchterm = request.getParameter("searchterm");
//	         if (searchterm == null) {
//	            out.println("<p>SearchTerm: MISSING</p>");
//	         } else {
//	            out.println("<p>SearchTerm: " + searchterm + "</p>");
//	         }
//
//	         String location = request.getParameter("location");
//	         if (location == null) {
//	            out.println("<p>Location: MISSING</p>");
//	         } else {
//	            out.println("<p>Location: " + location + "</p>");
//	         }
//
//	         String limit = request.getParameter("limit");
//	         if (searchterm == null) {
//	            out.println("<p>Limit: MISSING</p>");
//	         } else {
//	            out.println("<p>Limit: " + limit + "</p>");
//	         }
//
//
//
//	         String searchResponse = YelpAPI.getSearchResults(searchterm, location, limit);
//
//	         Gson gson = new Gson();
//
//	         SearchResponse searchResponseObj = gson.fromJson(searchResponse, SearchResponse.class);
//
//
//	         out.println("<p>Search Results in Json Format: </p>");
//	         out.println("<p> First name: " + searchResponseObj.getBusinesses().get(0).getName() + "</p>");
//	         out.println("<p> Address: " + searchResponseObj.getBusinesses().get(0).getLocation().getDisplay_address().get(0)+ "," +
//	        		 searchResponseObj.getBusinesses().get(0).getLocation().getDisplay_address().get(1) + "</p>");
//	         out.println("<p> Second name" + searchResponseObj.getBusinesses().get(1).getName() + "</p>");
//	         out.println("<p>" + searchResponse +"</p>");
//
//	         out.println("</body></html>");
//	      } finally {
//	         out.close();  // Always close the output writer
//	      }



////////////////////////////////////
	         String searchterm = request.getParameter("searchterm");
	         String location = request.getParameter("location");
	         String limit = request.getParameter("limit");

	         String searchResponse = YelpAPI.getSearchResults(searchterm, location, limit);
//
//	         Gson gson = new Gson();
//
//	         SearchResponse searchResponseObj = gson.fromJson(searchResponse, SearchResponse.class);
//	         response.sendRedirect("searchresult.html");

//////////////////////////////////
//		   response.setContentType("text/plain");
//		    response.setCharacterEncoding("UTF-8");
//		    response.getWriter().write(searchterm);
///////////////////
	         RequestDispatcher dispatcher = request.getRequestDispatcher("searchresult.jsp");
	         request.setAttribute("resultJson", searchResponse); // set your String value in the attribute
	         dispatcher.forward( request, response );

	   }

//	   // Redirect POST request to GET request.
//	   @Override
//	   public void doPost(HttpServletRequest request, HttpServletResponse response)
//	               throws IOException, ServletException {
//	      doGet(request, response);
//	   }

	   // Filter the string for special HTML characters to prevent
	   // command injection attack
	   private static String htmlFilter(String message) {
	      if (message == null) return null;
	      int len = message.length();
	      StringBuffer result = new StringBuffer(len + 20);
	      char aChar;

	      for (int i = 0; i < len; ++i) {
	         aChar = message.charAt(i);
	         switch (aChar) {
	             case '<': result.append("&lt;"); break;
	             case '>': result.append("&gt;"); break;
	             case '&': result.append("&amp;"); break;
	             case '"': result.append("&quot;"); break;
	             default: result.append(aChar);
	         }
	      }
	      return (result.toString());
	   }

}
