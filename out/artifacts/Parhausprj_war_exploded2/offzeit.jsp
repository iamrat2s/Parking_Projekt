<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Öffnungszeiten</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h1 {
            margin-top: 40px;
            margin-bottom: 20px;
        }
        form {
            margin-bottom: 40px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"] {
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 100%;
            max-width: 300px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }
        button[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<h1>Die Öffnungszeiten unseres Parkhauses aktualisieren:</h1>
<p>${openingHours}</p>
<form method="post" action="zeit-servlet">
    <label>Neue Öffnungszeiten:</label>
    <input type="text" name="openingHours">

    <a href="index.jsp"><button>Speichern</button></a>
</form>
<div>
    <h1 style="color:black;"><%= "Hier können Sie den Ticket preis änderen" %></h1>
    <h2>Geben Sie den neuen Preis an </h2>
    <form action="price-servlet" method="post">
        <label for="ticketPrice"> Neuer Ticketpreis:</label>
        <div align="center"><input type="text" id="ticketPrice" name="ticketPrice"><br><br></div>
        <div align="center"><button type="submit">Ticketpreis ändern</button></div>
    </form>
</div>
</body>
</html>
