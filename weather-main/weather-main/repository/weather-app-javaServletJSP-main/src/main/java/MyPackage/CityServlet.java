package MyPackage;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
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
    		 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String newcity = request.getParameter("city");
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weather","root","Pulkit@1234");
		String query = "insert into city_name values('"+newcity+"','"+user_email+"')";
		
		Statement st = con.createStatement();
		int status = st.executeUpdate(query);
		if (status != 0) {
			request.getRequestDispatcher("/GetCityServlet").forward(request, response);
		}
		else {
			out.print("<h1>byyyyyyyyyyyyyy</h1>");
		}
		//PreparedStatement ps=con.prepareStatement("insert into city_name values('"+newcity+"')");
		//ResultSet rs=ps.executeUpdate();
		//System.out.println(rs);
		
		
		
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
