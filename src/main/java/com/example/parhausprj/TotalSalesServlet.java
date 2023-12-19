package com.example.parhausprj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

@WebServlet(name = "TotalSalesServlet", value = "/TotalSales-servlet")
public class TotalSalesServlet extends HttpServlet {
    String style = "<style>\n" +
            "body {\n" +
            "    font-family: Arial, sans-serif;\n" +
            "    background-color: #FFEBB3;\n" +
            "    margin: 0;\n" +
            "    padding: 0;\n" +
            "}\n" +
            "\n" +
            "h1 {\n" +
            "    text-align: center;\n" +
            "    margin-top: 30px;\n" +
            "}\n" +
            "\n" +
            "table {\n" +
            "    width: 80%;\n" +
            "    margin: 30px auto;\n" +
            "    border-collapse: collapse;\n" +
            "    background-color: #fff;\n" +
            "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
            "}\n" +
            "\n" +
            "th, td {\n" +
            "    padding: 12px 15px;\n" +
            "    text-align: left;\n" +
            "    border-bottom: 1px solid #ddd;\n" +
            "}\n" +
            "\n" +
            "th {\n" +
            "    background-color: #f9f9f9;\n" +
            "}\n" +
            "\n" +
            "td:last-child {\n" +
            "    text-align: center;\n" +
            "}\n" +
            "\n" +
            ".center {\n" +
            "    text-align: center;\n" +
            "    margin-top: 20px;\n" +
            "}\n" +
            "\n" +
            ".button-container {\n" +
            "    text-align: center;\n" +
            "    margin-bottom: 20px;\n" +
            "}\n" +
            "\n" +
            ".button-container button {\n" +
            "    margin: 0 5px;\n" +
            "    padding: 10px 15px;\n" +
            "    font-size: 16px;\n" +
            "    background-color: #4CAF50;\n" +
            "    color: #fff;\n" +
            "    border: none;\n" +
            "    border-radius: 4px;\n" +
            "    cursor: pointer;\n" +
            "}\n" +
            "\n" +
            "</style>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n");
        out.println("<title>Ticket Information</title>");
        out.println(style);
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Ticket Information</h1>");
        out.println("<div class=\"button-container\">");
        out.println("<button onclick=\"window.location.href='?sort=true'\">Sortieren</button>");
        out.println("<button onclick=\"window.location.href='?paid=true'\">Paid Tickets</button>");
        out.println("<button onclick=\"window.location.href='?unpaid=true'\">Unpaid Tickets</button>");
        out.println("<button onclick=\"window.location.href='?showalltickets=true'\">Show all tickets</button>");
        out.println("</div>");

        out.println("<table>");
        out.println("<tr><th>Ticket Number</th><th>Entry time</th><th>Exit time</th><th>Amount Charged</th><th>Leaved</th><th>Lost</th></tr>");

        // Check if the 'sort' parameter is present in the URL and set to 'true'
        boolean sortTable = Boolean.parseBoolean(request.getParameter("sort"));

        // Check if the 'paid' parameter is present in the URL and set to 'true'
        boolean showPaidTickets = Boolean.parseBoolean(request.getParameter("paid"));

        boolean showunPaidTickets = Boolean.parseBoolean(request.getParameter("unpaid"));

        // Check if the 'reset' parameter is present in the URL and set to 'true'
        boolean showAllTickets = Boolean.parseBoolean(request.getParameter("showalltickets"));

        List<Ticket> tickets = Ticket.getAllTickets();
        List<Ticket> sortedTickets;

        if (showPaidTickets) {
            sortedTickets = sortTable ? Ticket.getSortedTicketsByPriceDescending() : Ticket.getPaidTickets();
        } else if (showunPaidTickets) {
            sortedTickets = sortTable ? Ticket.getSortedTicketsByPriceDescending() : Ticket.getunPaidTickets();
        } else {
            sortedTickets = sortTable ? Ticket.getSortedTicketsByPriceDescending() : tickets;
        }

        if (showAllTickets) {
            sortedTickets = tickets;
        }



        List<Ticket> lostTickets = Ticket.getTicketsByState();

        double[] TotalTicketPrice = {0.0};


        sortedTickets.forEach(ticket -> {
            out.println("<tr>");
            out.println("<td>" + ticket.getTicketNummer() + "</td>");
            out.println("<td>" + ticket.getEintrittszeit() + "</td>");

            if (ticket.getBezahlzeit() != null && ticket.state instanceof com.example.parhausprj.Ausgefahren) {
                out.println("<td>" + ticket.austrittszeit + "</td>");

                double ticketPrice = ticket.getPrice() + ticket.getVerlustGeb() + ticket.getNachzahlung();
                TotalTicketPrice[0] += ticketPrice;
                out.println("<td>" + ticket.rounded(ticketPrice) + "<span>&#8364;</span></td>");
                out.println("<td>Yes</td>");
            } else {
                out.println("<td>-</td>");
                out.println("<td>-</td>");
                out.println("<td>Nope</td>");
            }

            boolean isLost = lostTickets.contains(ticket);
            out.println("<td>" + isLost + "</td>");
            out.println("</tr>");
        });

        out.println("</table>");

        out.println("<div class=\"center\">");
        out.println("<h2>Total Sales: " + String.format("%.2f", TotalTicketPrice[0]) + "<span>&#8364;</span></h2>");
        out.println("</div>");

        double paidAvg = Ticket.getOutTickets().stream().mapToDouble(Ticket::getPrice).average().orElse(0.0);
        double paidPercentage = (double) Ticket.getOutTickets().size() / tickets.size() * 100.0;
        double verlustGAvg = Ticket.getOutTickets().stream().mapToDouble(Ticket::getVerlustGeb).average().orElse(0.0);
        double timeAvg = Ticket.getOutTickets().stream().mapToDouble(x ->
                Duration.between(x.getEintrittszeit(), x.getBezahlzeit()).toHours()
                        + (double) (Duration.between(x.getEintrittszeit(), x.getBezahlzeit()).toMinutes() % 60) / 60.0
        ).average().orElse(0.0);

        out.println("<html><head><title>Statistical Data Visualization</title>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>");
        out.println("<style>");
        out.println(".container {");
        out.println("display: flex;");
        out.println("flex-wrap: wrap;");
        out.println("justify-content: center;");
        out.println("}");
        out.println(".chart-container {");
        out.println("width: 20%;");
        out.println("padding: 20px;");
        out.println("}");
        out.println(".statistic-container {");
        out.println("width: 20%;");
        out.println("padding: 20px;");
        out.println("}");
        out.println("</style>");
        out.println("<script>");
        out.println("document.addEventListener('DOMContentLoaded', function() {");
        out.println("var paidPercentageData = {");
        out.println("labels: ['Paid Percentage', 'Remaining'],");
        out.println("datasets: [{");
        out.println("data: [" + paidPercentage + ", " + (100 - paidPercentage) + "],");
        out.println("backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)'],");
        out.println("borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'],");
        out.println("borderWidth: 1");
        out.println("}]");
        out.println("};");
        out.println("var ctx = document.getElementById('chart').getContext('2d');");
        out.println("new Chart(ctx, {");
        out.println("type: 'pie',");
        out.println("data: paidPercentageData");
        out.println("});");
        out.println("});");
        out.println("</script>");
        out.println("</head><body>");
        out.println("<h1>Statistical Data Visualization</h1>");
        out.println("<div class=\"container\">");

        // Paid Percentage
        out.println("<div class=\"chart-container\">");
        out.println("<h2>Paid Percentage</h2>");
        out.println("<canvas id=\"chart\"></canvas>");
        out.println("</div>");

        // Average Price
        out.println("<div class=\"statistic-container\">");
        out.println("<h2>Average Price</h2>");
        out.println("<p>" + String.format("%.2f", paidAvg) + "&#8364;</p>");
        out.println("</div>");

        // Average Lost Fees
        out.println("<div class=\"statistic-container\">");
        out.println("<h2>Average Lost Fees</h2>");
        out.println("<p>" + String.format("%.2f", verlustGAvg) + "&#8364;</p>");
        out.println("</div>");

        // Average Time Spent
        out.println("<div class=\"statistic-container\">");
        out.println("<h2>Average Time Spent</h2>");
        out.println("<p>" + String.format("%.2f", timeAvg) + "h</p>");
        out.println("</div>");

        out.println("</div></body></html>");

        out.println("<button onclick=\"window.location.href='index.jsp'\">Back to Home</button>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


