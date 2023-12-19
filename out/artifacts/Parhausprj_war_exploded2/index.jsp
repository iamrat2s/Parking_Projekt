<%@ page import="com.example.parhausprj.Offnungzeitenservlet" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Parkhauss 404 - Home</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
    header {
      background-color:#FFCA33;
      color: black;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 180px;
    }

    .btn-container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: calc(40vh - 30px);
    }
    .btn-container button {
      padding: 16px 24px;
      border: none;
      border-radius: 16px;
      font-size: 1.2rem;
      font-weight: bold;
      color: black;
      background-color: #FFCA33;
      cursor: pointer;
      transition: all 0.3s ease;
      margin: 0 16px;
    }
    .btn-container button:hover {
      background-color: #fff;
      color: #333;
    }

  </style>
</head>
<body>
<header>
  <h1>Welcome To Parkhauss 404</h1>
</header>
<section>
  <div style="text-align:center;">
    <h2>Opening Hours:</h2>
    <li>"Mo-Fr 9-18, Sa-Su 10-16"</li>

    <p> new Opning Hours : ${openingHours}</p>
     <% %>
  </div>
</section>
<div class="btn-container">
  <a href="ticket-servlet"><button>Get a Ticket</button></a>
  <a href="hello-servlet"><button>Pay for your Ticket</button></a>
  <a href="verlust-servlet"><button class="lost-btn">Lost your Ticket?</button></a>
  <a href="offzeit.jsp"><button class="lost-btn">for owner!</button></a>
</div>
</body>
</html>

