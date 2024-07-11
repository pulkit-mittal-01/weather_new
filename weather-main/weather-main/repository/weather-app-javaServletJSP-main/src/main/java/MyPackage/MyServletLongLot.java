package MyPackage;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
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
import com.google.gson.JsonArray;
import java.io.FileReader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;



/**
 * Servlet implementation class MyServletLongLot
 */
@WebServlet("/MyServletLongLot")
public class MyServletLongLot extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletLongLot() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//Open Weather API setup
List<Double> summ_avg = new ArrayList<>();
List<Double> winn_avg = new ArrayList<>();
if (request.getParameter("fix_city_id") != null) {
String file_name = "//"+(String) request.getParameter("fix_city_id");
String summ_file = file_name  + "_sum.txt";
String winn_file = file_name  + "_win.txt";
System.out.println(summ_file);
        //String textFilePath = "//delhi_sum.txt"; // Adjust the path as per your project structure

        // Obtain the InputStream for the text file
        InputStream inputStream = getClass().getResourceAsStream(summ_file);
       
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                // Read lines from the text file and print them to console
                while ((line = reader.readLine()) != null) {
                    System.out.println(summ_file);
                String data = reader.readLine().replace(",", "");
                String[] text = data.split(":");
                winn_avg.add(Double.parseDouble(text[1]));
                   
                }
                System.out.println(winn_avg);
            }
        } else {
            // Handle case where file is not found or cannot be read
            System.err.println("File not found or could not be read: " + summ_file);
        }

            InputStream inputStream_winn = getClass().getResourceAsStream(winn_file);
            if (inputStream_winn != null) {
                try (BufferedReader reader_winn = new BufferedReader(new InputStreamReader(inputStream_winn))) {
                    String line;

                    // Read lines from the text file and print them to console
                    while ((line = reader_winn.readLine()) != null) {
                    String data = reader_winn.readLine().replace(",", "");
                    String[] text = data.split(":");
                    summ_avg.add(Double.parseDouble(text[1]));
                       
                    }
                    System.out.println(summ_avg);
                }
            } else {
                // Handle case where file is not found or cannot be read
                System.err.println("File not found or could not be read: " + winn_file);
           
}
            double summ_min = summ_avg.stream().mapToDouble(num -> num).average().orElse(Double.NaN);
            double winn_min = winn_avg.stream().mapToDouble(num -> num).average().orElse(Double.NaN);
            request.setAttribute("summ_min", summ_min);
    request.setAttribute("winn_min", winn_min);
    request.getRequestDispatcher("/GetCityServlet").forward(request, response);
       
}
}
}