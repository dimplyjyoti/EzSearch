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

	         String searchterm = request.getParameter("searchterm");
	         String location = request.getParameter("location");
	         String limit = request.getParameter("limit");
	         if(limit.isEmpty()) {
	        	 limit = "5";
	         }

	         String searchResponse = YelpAPI.getSearchResults(searchterm, location, limit);
	         Gson gson = new Gson();

	         SearchResponse searchResponseObj = gson.fromJson(searchResponse, SearchResponse.class);

	         RequestDispatcher dispatcher = request.getRequestDispatcher("searchresult.jsp");
	         request.setAttribute("resultJson", searchResponseObj); // set your String value in the attribute
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
