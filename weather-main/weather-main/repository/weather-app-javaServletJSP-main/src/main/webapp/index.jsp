<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <title>Hello, world!</title>
    <style>
      .current-weather {
    background-color: #0073e6;
    color: white;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    text-align: center;
    box-shadow: 0 0 5px rgb(122, 120, 120);
    
}

.current-weather_new{
    background-color: white;
    color: black;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    text-align: center;
    box-shadow: 0 0 5px rgb(122, 120, 120);
}

.forecast-day {
            background-color: #ffffff;
            padding: 20px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

    </style>
  </head>
  <body style="background-color: #333;">
  <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("email")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("login.html");
%>
    <nav class="navbar navbar-light " style="background-color: #333; display: flex; justify-content: space-evenly; margin-bottom: 4%;">
        <div class="row" style="display: flex; width: 100%;">
            <div class="col-lg-6">
                <div class="container-fluid">
                    <form class="d-flex" action=GetCityServlet method="post">
                      <input type="text" id="Longitude" name="Longitude" class="form-control me-2" placeholder="Longitude E.g. 22.8" style="width: 40%;"/>
                        <input type="text" id="Latitude" name="Latitude" class="form-control me-2" placeholder="Latitude E.g., 22.0"  style="margin-right:1px; width: 40%;"/>
                
                        <button type="submit" class="btn btn-outline" style="background-color: #007bff; color: white;">Get</button>
                    </form>
                  </div>
            </div>
            <div class="col-lg-5">
                <div class="container-fluid">
                    <form class="d-flex" action="GetCityServlet" method="post">
                      <input class="form-control me-2" type="search" id="city" name="city" placeholder="Search city" aria-label="Search" style="margin-right:1px; width: 50%;"/>
                      <button class="btn btn-outline" type="submit" style="background-color: #007bff; color: white;">Search</button>
                    </form>
                  </div>
            </div>
            <div class="col-lg-1" style="margin-left: 0px;">
                <ul class="nav justify-content-end">
                    <div class="btn-group">
                        <button type="button" class="btn btn-danger dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                          Action
                        </button>
                        
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#" style="color: black;"><%=userName %></a></li>
                          <li><a class="dropdown-item" href="LogoutServlet" style="color: black;">Logout</a></li>


                        </ul>
                      </div>
                </ul>
            </div>
        </div>
      </nav>
    <div class="container" style="margin-bottom: 3%;">
      <div class="row">
     <div class="col-lg-9">
            <div class="current-weather">
                <div class="row" style="display: flex; justify-content: start; justify-items: start; align-items: center; margin-bottom: 3%;">
                   <div class="col-lg-2"> <h5>Date :</h5></div>
                   <div class="col-lg-2" style="margin-left: -5%;"><span >${date}</span></div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <h1 style="font-size: 80px;"><i class="fa fa-thermometer-half" aria-hidden="true"></i> ${temperature} °C</h1>
                        <h1 style="font-size: 40px;">${city}</h1>
                    </div>
                    <div class="col-lg-3" style="margin-top: 1%;">
                        <div class="container" style="justify-content: start; display: grid;">
                            <p>${weatherCondition}</p>
                <p>Humidity: ${humidity}%</p>
                <p>Wind: ${windSpeed}km/hr</p>
               
                        </div>
                    </div>
                    <div class="col-lg-3" style="margin-top: 1%;">
                        <div class="container" style="justify-content: start; display: grid;">
                            <p>Summer min : ${summ_min}°C</p>
                <p>Winter min: ${winn_min}°C</p>
                 <% 
    String message = (String) request.getAttribute("city");
    
    if (message != null && !message.isEmpty()) { 
    %>
        <form id="popupForm" action="CityServlet" method="post">
        <input type="hidden" name="city" placeholder="Enter value" value="<%= message %>">
                    <button class="btn btn-outline" type="submit" style="background-color: red; color: white;">Add city</button>
                </form>
    <% } else { %>
    <% } %>
                
            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="current-weather_new" >
                <form action="MyServletLongLot" method="post" class="my_city" >
                    <select name="fix_city_id" id="city_id" class="city">
                        <option value="">Select a city</option>
                        <option value="delhi">Delhi</option>
                        <option value="jaipur">Jaipur</option>
                        <option value="mumbai">Mumbai</option>
                        <option value="pune">Pune</option>
                    </select>
                    <button>
                        submit
                    </button>
                </form>
               
            </div>
        </div>
      </div>
    </div>
    <div class="container">
        <div class="row" style="gap: 40px;">
        
<% 
                HashMap<String, HashMap<String, String>> city_data = (HashMap<String, HashMap<String, String>>) request.getAttribute("city_data_list");
                for (String city : city_data.keySet()) {
                    HashMap<String, String> data = city_data.get(city);
            %>
            <div class="col-lg-2 forecast-day ">
                <div class="row" style="margin-bottom: 8px;">
                    <div class="col-lg-9">
                        <h5><%= city %></h5>
                    </div>
                    <div class="col-lg-3" style="display: flex; gap: 5px; align-items: center; margin-left: -10px;">
                        <i class="fa fa-thermometer-half" aria-hidden="true"></i> <%= data.get("temperature") %>°C
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="display: flex; gap: 10px;"><h6>Weather:</h6><span> <%= data.get("weatherCondition") %></span></div>
                    <div class="col-lg-12" style="display: flex; gap: 10px;"><h6>Humidity: </h6><span><%= data.get("humidity") %>%</span></div>
                    <div class="col-lg-12" style="display: flex; gap: 10px;"><h6>Wind:</h6><span> <%= data.get("windSpeed") %>km/hr</span></div>
                </div>
                
            </div>

            <% } %>
            
            
            
   
            

            
        </div>
    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>