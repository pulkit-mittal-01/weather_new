package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URL;


/**
 * 
 * Servlet implementation class CitydataServlet
 */
@WebServlet("/CitydataServlet")
public class CitydataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitydataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ArrayList<String> myList = (ArrayList<String>) request.getAttribute("city_list");
		 HashMap<String,HashMap<String, String>> city_data = new HashMap<String,HashMap<String, String>>();
		 
		int size = myList.size();
		if (size!=0) {
			for (int i = 0; i < size; i++) {
	            String city = myList.get(i);
	            HashMap<String, String> data = new HashMap<String, String>();
	            // Perform operations on each element (for example, print it)
	            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&appid=1a9b7190193a6b34f5d93047fe6b5f65";
	            
	            URL url = new URL(apiUrl);
	            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("GET");
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
				String tempInCelsius = String.valueOf((int)(tempInKelvin - 273.15));
				
				//Humidty
				String humidity = String.valueOf(jsonObject.getAsJsonObject("main").get("humidity").getAsInt());
				
				//wind speed
				String windSpeed = String.valueOf(jsonObject.getAsJsonObject("wind").get("speed").getAsDouble());
				//visibitity
				int visibilityInMeter = jsonObject.get("visibility").getAsInt();
				String visibility = String.valueOf(visibilityInMeter / 1000);
				//weather condition
		        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		        //cloud condition
		        String cloudCover = String.valueOf(jsonObject.getAsJsonObject("clouds").get("all").getAsInt());
		        
		        
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
		        data.put("visibility", visibility);
	            data.put("temperature", tempInCelsius);
	            data.put("weatherCondition", weatherCondition);
	            data.put("humidity", humidity);
	            data.put("windSpeed", windSpeed);
	            data.put("cloudCover", cloudCover);
	            data.put("currentTime", formattedTime);
	            data.put("weatherData", responseContent.toString());
	            city_data.put(city, data);
	        }
			request.setAttribute("city_data_list", city_data);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else {
			 request.getRequestDispatcher("/index.jsp").forward(request, response);
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
