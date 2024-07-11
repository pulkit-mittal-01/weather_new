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
import java.util.Collections;
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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MinMaxtempServlet
 */
@WebServlet("/MinMaxtempServlet")
public class MinMaxtempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MinMaxtempServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Double> summer_min = new ArrayList<>();
		List<Double> winter_min = new ArrayList<>();
		String city_name = (String) request.getAttribute("city");
		String apiUrl1 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2023-06-01&end_date=2023-06-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url1 = new URL(apiUrl1);
		System.out.println(url1);
		HttpURLConnection connection1 = (HttpURLConnection)url1.openConnection();
		connection1.setRequestMethod("GET");
		
		InputStream inpStream1 = connection1.getInputStream();
		InputStreamReader reader1 = new InputStreamReader(inpStream1);
		
		//want to store in string
		StringBuilder responseContent1 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner1 = new Scanner(reader1);
		while(scanner1.hasNext()) {
			responseContent1.append(scanner1.nextLine());
		}
		scanner1.close();
		String apiUrl2 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2023-07-01&end_date=2023-07-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url2 = new URL(apiUrl2);
		System.out.println(url2);
		HttpURLConnection connection2 = (HttpURLConnection)url2.openConnection();
		connection2.setRequestMethod("GET");
		
		InputStream inpStream2 = connection2.getInputStream();
		InputStreamReader reader2 = new InputStreamReader(inpStream2);
		
		//want to store in string
		StringBuilder responseContent2 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner2 = new Scanner(reader2);
		while(scanner2.hasNext()) {
			responseContent2.append(scanner2.nextLine());
		}
		scanner2.close();
		String apiUrl3 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2023-08-01&end_date=2023-08-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url3 = new URL(apiUrl3);
		System.out.println(url3);
		HttpURLConnection connection3 = (HttpURLConnection)url3.openConnection();
		connection3.setRequestMethod("GET");
		
		InputStream inpStream3 = connection3.getInputStream();
		InputStreamReader reader3 = new InputStreamReader(inpStream3);
		
		//want to store in string
		StringBuilder responseContent3 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner3 = new Scanner(reader3);
		while(scanner3.hasNext()) {
			responseContent3.append(scanner3.nextLine());
		}
		scanner3.close();
		Gson gson = new Gson();
		JsonObject jsonObject1 = gson.fromJson(responseContent1.toString(), JsonObject.class);
		JsonArray dataArray1 = jsonObject1.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray1) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            summer_min.add(tempValue);
        }
        JsonObject jsonObject2 = gson.fromJson(responseContent2.toString(), JsonObject.class);
		JsonArray dataArray2 = jsonObject2.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray2) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            summer_min.add(tempValue);
        }
        JsonObject jsonObject3 = gson.fromJson(responseContent3.toString(), JsonObject.class);
		JsonArray dataArray3 = jsonObject3.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray3) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            summer_min.add(tempValue);
        }
        
        
        
        
        String apiUrl4 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2023-10-01&end_date=2023-10-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url4 = new URL(apiUrl4);
		System.out.println(url4);
		HttpURLConnection connection4 = (HttpURLConnection)url4.openConnection();
		connection4.setRequestMethod("GET");
		
		InputStream inpStream4 = connection4.getInputStream();
		InputStreamReader reader4 = new InputStreamReader(inpStream4);
		
		//want to store in string
		StringBuilder responseContent4 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner4 = new Scanner(reader4);
		while(scanner4.hasNext()) {
			responseContent4.append(scanner4.nextLine());
		}
		scanner4.close();
		String apiUrl5 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2024-01-01&end_date=2024-01-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url5 = new URL(apiUrl5);
		System.out.println(url5);
		HttpURLConnection connection5 = (HttpURLConnection)url5.openConnection();
		connection5.setRequestMethod("GET");
		
		InputStream inpStream5 = connection5.getInputStream();
		InputStreamReader reader5 = new InputStreamReader(inpStream5);
		
		//want to store in string
		StringBuilder responseContent5 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner5 = new Scanner(reader5);
		while(scanner5.hasNext()) {
			responseContent5.append(scanner5.nextLine());
		}
		scanner5.close();
		String apiUrl6 = "https://api.weatherbit.io/v2.0/history/subhourly/city?city="+city_name+"&start_date=2024-02-01&end_date=2024-02-31&key=9ec84aa6686f4b66bad6d477baa28896";
		URL url6 = new URL(apiUrl6);
		System.out.println(url6);
		HttpURLConnection connection6 = (HttpURLConnection)url6.openConnection();
		connection6.setRequestMethod("GET");
		
		InputStream inpStream6 = connection6.getInputStream();
		InputStreamReader reader6 = new InputStreamReader(inpStream6);
		
		//want to store in string
		StringBuilder responseContent6 = new StringBuilder();
		
		//create scanner object  to take input from reader.
		Scanner scanner6 = new Scanner(reader6);
		while(scanner6.hasNext()) {
			responseContent6.append(scanner6.nextLine());
		}
		scanner6.close();
		JsonObject jsonObject4 = gson.fromJson(responseContent4.toString(), JsonObject.class);
		JsonArray dataArray4 = jsonObject4.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray4) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            winter_min.add(tempValue);
        }
        JsonObject jsonObject5 = gson.fromJson(responseContent5.toString(), JsonObject.class);
		JsonArray dataArray5 = jsonObject5.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray5) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            winter_min.add(tempValue);
        }
        JsonObject jsonObject6 = gson.fromJson(responseContent6.toString(), JsonObject.class);
		JsonArray dataArray6 = jsonObject6.getAsJsonArray("data");

        // Iterate over elements in the 'data' array
        for (JsonElement element : dataArray6) {
            // Extract 'temp' field from each element
            double tempValue = element.getAsJsonObject().get("temp").getAsDouble();
            
            // Print or use the extracted temperature value
            winter_min.add(tempValue);
        }
        
        double summ_min = summer_min.stream().mapToDouble(num -> num).average().orElse(Double.NaN);
        double winn_min = winter_min.stream().mapToDouble(num -> num).average().orElse(Double.NaN);
        
		// = Collections.min(summer_min);
		//double winn_min = Collections.min(winter_min);
		request.setAttribute("summ_min", summ_min);
		request.setAttribute("winn_min", winn_min);
		request.getRequestDispatcher("CitydataServlet").forward(request, response);
	}

}
