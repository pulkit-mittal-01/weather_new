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
/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  

        PrintWriter out=response.getWriter(); 

        //request.getRequestDispatcher("login.html").include(request, response);  

        HttpSession session=request.getSession();

        String name=(String)session.getAttribute("username");  

        

       
        

        session.invalidate();  
        Cookie[] cookies = request.getCookies();
        
        
        
        if (cookies != null) {
        	
        	// Iterate through the cookies and set their max age to 0
        	
        	for (Cookie cookie : cookies) {
        		
        		cookie.setMaxAge(0);
        		
        		response.addCookie(cookie); // Update the cookie in client's browser
        		
        	}
        	
        }

        response.sendRedirect("login.html");
        


        out.print("You are successfully logged out!");  

          

        out.close(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
