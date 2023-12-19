<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="style.css">
<head>
    <title>Öffnungszeiten</title>

</head>
<body>
<div class="container">
    <h1>Parking administration</h1>

    <div class="form-wrapper">
        <h2>Update opening hours:</h2>
        <form method="post" action="zeit-servlet">
            <label for="openingHours">Enter the new opening hours:</label>
            <input type="text" id="openingHours" name="openingHours">
            <button type="submit">Submit</button>
        </form>
    </div>

    <div class="form-wrapper">
        <h2>Update the ticket price:</h2>
        <form action="price-servlet" method="post">
            <label for="ticketPrice">Enter the new price:</label>
            <input type="text" id="ticketPrice" name="ticketPrice">
            <button type="submit">Submit</button>
        </form>
    </div>

    <div class="links-wrapper">
        <a href="visualisation">Visualization</a>
        <a href="TotalSales-servlet">Total sales</a>
        <a href="index.jsp" style="background-color: #FF0000;">Back to Home</a>
    </div>
    <div class="form-wrapper">
        <h2> Show Feedback:</h2>
        <a href="feedback-servlet"><button> Show feedbacks </button></a>
    </div>
</div>
</body>
</html>
