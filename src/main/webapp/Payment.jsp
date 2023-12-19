<!DOCTYPE html>
<html lang="en">
<head>
    <title>Credit Card Payment</title>
    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            position: relative;
        }
    </style>
</head>
<body>
<script>
    // Funktion zum Wechseln der Bilder und Weiterleitung
    function wechseln() {
        var container = document.getElementById("imageContainer");
        var bilder = container.getElementsByTagName("img");

        // Erstes Bild anzeigen
        bilder[0].style.display = "block";

        // Timer für den Wechsel zum zweiten Bild
        setTimeout(function() {
            // Erstes Bild ausblenden
            bilder[0].style.display = "none";

            // Zweites Bild anzeigen
            bilder[1].style.display = "block";

            // Timer für den Wechsel zum dritten Bild
            setTimeout(function() {
                // Zweites Bild ausblenden
                bilder[1].style.display = "none";

                // Drittes Bild anzeigen
                bilder[2].style.display = "block";

                // Timer für die Weiterleitung zum ConfirmPaymentServlet
                setTimeout(function() {
                    // Preiswert aus dem URL-Parameter lesen


                    // Weiterleitung zum ConfirmPaymentServlet mit Preiswert als URL-Parameter
                    window.location.href = "confirm-payment?ticketNumber=<%= request.getParameter("ticketNumber") %>&autonummer=<%= request.getParameter("autonummer") %>";

                }, 2000); // 5 Sekunden für das dritte Bild anzeigen
            }, 3000); // 5 Sekunden für das zweite Bild anzeigen
        }, 7000); // 5 Sekunden für das erste Bild anzeigen
    }

    // Timer, um den Bildwechsel nach 5 Sekunden auszuführen
    setTimeout(wechseln, 1);
</script>

<div id="imageContainer" class="center">
    <img src="https://cdn.myportfolio.com/d0221460a6a935e27385fa4a2e426bc2/9f620971-619d-484b-a89b-8487f007f01d_rw_600.gif?h=4ea5638c1d37fd0f434808cba49e1acc" alt="Bild 1" style="display: none;">
    <img src="https://i.gifer.com/VM3x.gif" alt="Bild 2" style="display: none;">
    <img src="https://i.gifer.com/7efs.gif" alt="Bild 3" style="display: none;">
</div>
</body>
</html>
