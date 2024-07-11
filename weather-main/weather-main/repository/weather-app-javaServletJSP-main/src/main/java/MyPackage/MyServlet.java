package MyPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		//Open Weather API setup
		String myApiKey = "1a9b7190193a6b34f5d93047fe6b5f65";
		//getting the city name from the form input
		String apiUrl = (String) request.getAttribute("apiUrl");
		System.out.println(apiUrl);
		//Create the URL of the 
		//String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city_name+ "&appid="+ myApiKey;
		String city_name = (String) request.getAttribute("city");
		
		try {
			
			//API Integration
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			
			//reading data from network;
			InputStream inpStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inpStream);
			
			//want to store in string
			StringBuilder responseContent = new StringBuilder();
			
			//create scanner object  to take input from reader.
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNext()) {
				responseContent.append(scanner.nextLine());
			}
			scanner.close();
			
			//now the response should be made in to json format
			// typecasting 
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);;
			
			System.out.println(jsonObject);
			//Temperature
			double tempInKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
			int tempInCelsius = (int)(tempInKelvin - 273.15);
			
			//Humidty
			int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
			
			//wind speed
			double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
			//visibitity
			int visibilityInMeter = jsonObject.get("visibility").getAsInt();
			int visibility = visibilityInMeter / 1000;
			//weather condition
	        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
	        //cloud condition
	        int cloudCover = jsonObject.getAsJsonObject("clouds").get("all").getAsInt();
	        
	        
	        // Date & Time
	     	//long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
	     	//String date = new Date(dateTimestamp).toString();
	     			
	        
	     	// Date & Time
	        long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
	        SimpleDateFormat sdfDate = new SimpleDateFormat("EEE MMM dd yyyy");
	        String date = sdfDate.format(new Date(dateTimestamp));

	        // Fetching the current time
	        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	        String formattedTime = sdfTime.format(new Date());


	        // Set the data as request attributes (for sending to the jsp page)
	        request.setAttribute("date", date);
	        if(jsonObject.get("name").getAsString()!= null) {
	        	request.setAttribute("city", jsonObject.get("name").getAsString());
	        }
	        //request.setAttribute("city", city_name);
	        request.setAttribute("visibility",visibility);
	        request.setAttribute("temperature", tempInCelsius);
	        request.setAttribute("weatherCondition", weatherCondition); 
	        request.setAttribute("humidity", humidity);    
	        request.setAttribute("windSpeed", windSpeed);
	        request.setAttribute("cloudCover", cloudCover);
	        request.setAttribute("currentTime", formattedTime);
	        request.setAttribute("weatherData", responseContent.toString());
	        request.setAttribute("city_list", request.getAttribute("city_list"));

	        connection.disconnect();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------------------------------------");
		System.out.println(city_name);
		if(city_name!=null) {
			request.getRequestDispatcher("/MinMaxtempServlet").forward(request, response);
			
		}
		else {
			request.getRequestDispatcher("CitydataServlet").forward(request, response);
		}
		
        
     // Forward the request to the weather.jsp page for rendering

        
	}

}
