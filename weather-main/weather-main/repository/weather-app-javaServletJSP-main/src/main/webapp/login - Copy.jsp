<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Login Page</title>

    <style>

        body {

            background-color: #f0f0f0; /* Light gray background */

            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

            margin: 0;

            padding: 0;

            display: flex;

            justify-content: center;

            align-items: center;

            height: 100vh;

        }

        .login-box {

            background-color: #ffffff; /* White background */

            padding: 30px;

            border-radius: 10px;

            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);

            text-align: center;

            max-width: 400px;

            width: 90%;

        }

        .login-box h2 {

            color: #333333; /* Dark gray text */

            margin-bottom: 20px;

        }

        .login-box input[type="text"],

        .login-box input[type="password"] {

            width: calc(100% - 20px);

            padding: 12px;

            margin: 10px 0;

            border: 1px solid #cccccc; /* Light gray border */

            border-radius: 5px;

            box-sizing: border-box;

            font-size: 16px;

        }

        .login-box input[type="text"]:focus,

        .login-box input[type="password"]:focus {

            outline: none;

            border-color: #4CAF50; /* Green border when focused */

        }

        .login-box input[type="submit"] {

            background-color: #4CAF50; /* Green background */

            color: white;

            padding: 12px 20px;

            border: none;

            border-radius: 5px;

            cursor: pointer;

            font-size: 16px;

            transition: background-color 0.3s ease;

        }

        .login-box input[type="submit"]:hover {

            background-color: #45a049; /* Darker green on hover */

        }

        .login-box .error-message {

            color: #e74c3c; /* Red text for error messages */

            margin-bottom: 10px;

        }

        .login-box .register-link {

            margin-top: 15px;

            font-size: 14px;

            color: #3498db; /* Blue link color */

        }

        .login-box .register-link a {

            color: #3498db; /* Blue link color */

            text-decoration: none;

        }

        .login-box .register-link a:hover {

            text-decoration: underline;

        }

    </style>

</head>

<body>

    <div class="login-box">

        <h2>Login to Weather App</h2>

        

        <%-- Error message handling --%>

        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>

        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>

            <p class="error-message"><%= errorMessage %></p>

        <% } %>

        

        <form action="LoginServlet" method="post">

            <input type="text" name="email" placeholder="Email id" required><br>

            <input type="password" name="password" placeholder="Password" required><br>

            <input type="submit" value="Login">
            <button type="submit">
					<i class="fas fa-search"></i> Get Weather
				</button>

        </form>

    </div>

</body>

</html>

