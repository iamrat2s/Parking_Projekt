<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cash Payment Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFEBB3;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            padding: 50px;
        }
        h1 {
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            margin-bottom: 11px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: inline-block;
            width: 150px;
            text-align: left;
        }
        .form-group input {
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 200px;
            box-sizing: border-box;
        }
        .form-group button {
            background-color: #333;
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Cash Payment Success</h1>
    <div class="form-group">
        <label for="ticketNumber">Ticket Number:</label>
        <input type="text" id="ticketNumber" name="ticketNumber" value="<%= request.getParameter("ticketNumber") %>" readonly>
    </div>
    <div class="form-group">
        <label for="autonummer">Autonummer:</label>
        <input type="text" id="autonummer" name="autonummer" value="<%= request.getParameter("autonummer") %>" readonly>
    </div>
    <div class="form-group">
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="<%= request.getParameter("price") %>" readonly>
    </div>


    <div class="form-group">
        <label for="amountPaid">Amount Paid:</label>
        <input type="text" id="amountPaid" name="amountPaid">
    </div>
    <div class="form-group">
        <label for="change">Change:</label>
        <input type="text" id="change" name="change" readonly>
    </div>
    <button onclick="calculateChange()">Calculate Change</button>
</div>

<script>
    function calculateChange() {
        var amountPaid = parseFloat(document.getElementById("amountPaid").value);
        var price = parseFloat(document.getElementById("price").value);

        var change = amountPaid - price;

        if (change >= 0) {
            document.getElementById("change").value = change.toFixed(2);
            setTimeout(paymentSuccessful, 3000);
        } else {
            document.getElementById("change").value = "";
            alert("Amount paid is less than the price. Please enter a valid amount.");
        }
    }

    function paymentSuccessful() {
        alert("Payment Successful");
        window.location.href = "index.jsp";
    }
</script>
</body>
</html>
