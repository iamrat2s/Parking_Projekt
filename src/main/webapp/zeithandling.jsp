<!DOCTYPE html >
<html lang="en">

<head>
    <title>Parkhaus Zeit-Handling</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
        }

        p {
            text-align: center;
            margin-bottom: 20px;
        }

        .actions {
            text-align: center;
        }

        .actions button {
            padding: 8px 16px;
            margin: 0 10px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
        }

        .actions button:hover {
            background-color: #45a049;
        }

        label {
            font-weight: bold;
        }

        input[type="datetime-local"] {
            padding: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .actions-row {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10px;
        }

        .actions-row button {
            margin: 0 5px;
        }

        .back-btn-container {
            text-align: center;
            margin-top: 20px;
        }

        .back-btn-container a {
            text-decoration: none;
        }

        .back-btn-container button {
            padding: 8px 16px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            background-color: #ff0000;
            color: #fff;
            border: none;
            border-radius: 4px;
        }

        .back-btn-container button:hover {
            background-color: #cc0000;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Parkhaus Zeit-Handling</h1>
    <p>Aktuelle Zeit:
        <%= ((java.time.LocalDateTime)request.getAttribute("currentTime")).format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) %>
    </p>

    <div class="actions">
        <div class="actions-row">
            <form action="zeithandling" method="post">
                <input type="hidden" name="action" value="reset">
                <button type="submit">reset time</button>
            </form>

            <form action="zeithandling" method="post">
                <input type="hidden" name="action" value="add5min">
                <button type="submit">+5 Min</button>
            </form>

            <form action="zeithandling" method="post">
                <input type="hidden" name="action" value="add15min">
                <button type="submit">+15 Min</button>
            </form>

            <form action="zeithandling" method="post">
                <input type="hidden" name="action" value="add1hour">
                <button type="submit">+1 Stunde</button>
            </form>
        </div>

        <form action="zeithandling" method="post">
            <input type="hidden" name="action" value="timewarp">
            <label for="time">Neue Zeit:</label>
            <input type="datetime-local" id="time" name="time" required>
            <button type="submit">move time forward</button>
        </form>

        <div class="back-btn-container">
            <a href="index.jsp"><button type="button">Back to Home</button></a>
        </div>
    </div>
</div>
</body>

</html>
