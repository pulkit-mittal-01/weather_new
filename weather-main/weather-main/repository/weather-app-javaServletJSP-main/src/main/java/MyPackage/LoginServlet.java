package MyPackage;



import jakarta.servlet.ServletException;

import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;

import java.io.IOException;

import java.sql.Connection;

import jakarta.servlet.http.Cookie;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.Statement;

/**

 * Servlet implementation class LoginServlet

 */

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

       

    /**

     * @see HttpServlet#HttpServlet()

     */

    public LoginServlet() {

        super();

        // TODO Auto-generated constructor stub

    }



	/**

	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub

		String userId = "";

		

		if (request.getParameter("email") != null) {

			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			userId = request.getParameter("email");

			String pass = request.getParameter("password");

			System.out.print(pass);

			try

				{

				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/weather","root","Pulkit@1234");

				PreparedStatement ps=con.prepareStatement("select * from login where email=? and password=? ");

				ps.setString(1, userId);

				ps.setString(2, pass);

				ResultSet rs=ps.executeQuery();

				List<String> dataList = new ArrayList<>();

				if(rs.next())

				{

					

					PreparedStatement ps1=con.prepareStatement("select * from city_name where user=?");

					ps1.setString(1, userId);

					ResultSet rs1=ps1.executeQuery();

					while (rs1.next()) {

						

			            String city = rs1.getString("city");

			            

			            // Example: Format data as needed (here, just adding to list)

			            dataList.add(city);

			        }

					request.setAttribute("city_list", dataList);
					request.setAttribute("user_email", userId);
					
						Cookie loginCookie = new Cookie("email",userId);

						//setting cookie to expiry in 30 mins

						loginCookie.setMaxAge(30*60);

						response.addCookie(loginCookie);

		

						HttpSession session = request.getSession(true);

				        session.setAttribute("username", userId);

				        request.getRequestDispatcher("CitydataServlet").forward(request, response);

				        //response.sendRedirect("index.jsp");

					}

					else {

						Cookie loginCookie = new Cookie("email",userId);

						//setting cookie to expiry in 30 mins

						loginCookie.setMaxAge(30*60);

						response.addCookie(loginCookie);

		

						HttpSession session = request.getSession();

				        session.setAttribute("username", userId);

				        response.sendRedirect("login.html");

					}
				

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

;