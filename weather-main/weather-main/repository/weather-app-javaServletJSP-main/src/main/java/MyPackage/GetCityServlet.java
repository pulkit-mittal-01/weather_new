package MyPackage;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import jakarta.servlet.http.Cookie;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.List;
/**
/**
 * Servlet implementation class GetCityServlet
 */
@WebServlet("/GetCityServlet")
public class GetCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie loginCookie = null;
    	Cookie[] cookies = request.getCookies();
    	
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("email")){
    			loginCookie = cookie;
    			break;
    		}
    	}
    	}
    	String user_email = "";
    	if(loginCookie != null){
    		 user_email = loginCookie.getValue();

    	}
		try
		{
			String city_name = null;
			String apiUrl = "";
			String Latitude  = "";
			String Longitude = "";
			if (request.getParameter("city") != null) {
				city_name = request.getParameter("city");
				apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city_name+ "&appid=1a9b7190193a6b34f5d93047fe6b5f65";
			}
			else if (request.getParameter("city_id") != null) {
				city_name = request.getParameter("city_id");
				apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city_name+ "&appid=1a9b7190193a6b34f5d93047fe6b5f65";
			}
			
			else if (request.getParameter("Longitude") != null && request.getParameter("Latitude") != null) {
				Longitude = request.getParameter("Longitude");
				Latitude = request.getParameter("Latitude");
				apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+Latitude+"&lon="+Longitude+"&appid=1a9b7190193a6b34f5d93047fe6b5f65";
			}
			List<String> dataList = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weather","root","Pulkit@1234");

				PreparedStatement ps1=con.prepareStatement("select * from city_name where user=?");
				ps1.setString(1, user_email);
				ResultSet rs1=ps1.executeQuery();
				while (rs1.next()) {
					
		            String city = rs1.getString("city");
		            
		            // Example: Format data as needed (here, just adding to list)
		            dataList.add(city);
		        }
				request.setAttribute("city", city_name);
				request.setAttribute("long", Longitude);
				request.setAttribute("apiUrl", apiUrl);
				request.setAttribute("city_list", dataList);
		        // Forward the request to ReceiverServlet
				request.getRequestDispatcher("/MyServlet").forward(request, response);
		   
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			//request.setAttribute("city", city_name);
			//request.setAttribute("apiUrl", apiUrl);
			//request.setAttribute("city_list", dataList);
        // Forward the request to ReceiverServlet
        	//request.getRequestDispatcher("/MyServlet").forward(request, response);
        //request.getRequestDispatcher("/MyServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
