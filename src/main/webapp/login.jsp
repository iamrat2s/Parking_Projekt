<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F3F7F9;
            margin: 0;
            padding: 0;
        }

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            max-width: 400px;
            background-color: #FFFFFF;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333333;
            margin-bottom: 30px;
        }

        form {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #555555;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            width: 100%;
            max-width: 300px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .password-toggle {
            display: flex;
            align-items: center;
            margin-top: 10px;
            color: #555555;
        }

        .password-toggle input[type="checkbox"] {
            margin-right: 5px;
        }

        .password-toggle label {
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #FFFFFF;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-weight: bold;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: #FF0000;
            text-align: center;
            margin-top: 10px;
        }
    </style>
    <script>
        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("password");
            var passwordToggle = document.getElementById("password-toggle");

            if (passwordToggle.checked) {
                passwordInput.type = "text";
            } else {
                passwordInput.type = "password";
            }
        }
    </script>
</head>

<%

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if ("admin".equals(username) && "1111".equals(password)) {
        // Weiterleitung zur offzeit.jsp, wenn die Anmeldeinformationen korrekt sind
        response.sendRedirect("offzeit.jsp");
    } else if (username != null || password != null) {
        // Ansonsten wird eine Fehlermeldung angezeigt
        out.println("<p class=\"error-message\">Invalid login credentials. Please try again.</p>");
    }
%>

<body>
<div class="center">
    <div class="login-container">
        <h1>Admin Login</h1>
        <form action="login.jsp" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password:</label>
            <div class="password-toggle">
                <input type="checkbox" id="password-toggle" onclick="togglePasswordVisibility()">
                <label for="password-toggle">Show Password</label>
            </div>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>
