package com.example.parhausprj;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message = "Pay your ticket";
    public  static double  price = 0;
    public static String style  = "<style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            background-color:#FFEBB3;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        form {\n" +
            "            margin-bottom: 40px;\n" +
            "        }\n" +
            "        label {\n" +
            "            display: block;\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "        input[type=\"text\"] {\n" +
            "            padding: 5px;\n" +
            "            font-size: 16px;\n" +
            "            border: 1px solid #ccc;\n" +
            "            border-radius: 3px;\n" +
            "            width: 100%;\n" +
            "            max-width: 300px;\n" +
            "            box-sizing: border-box;\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "        input[type=\"submit\"] {\n" +
            "            background-color: #333;\n" +
            "            color: #fff;\n" +
            "            padding: 5px;\n" +
            "            border: none;\n" +
            "            border-radius: 3px;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "        * {\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .row {\n" +
            "            display: flex;\n" +
            "        }\n" +
            "\n" +
            "        .column {\n" +
            "            flex: 50%;\n" +
            "            padding: 10px;\n" +
            "            height: 740px;\n" +
            "\n" +
            "        }\n" + "div {\n" +
            "  position: absolute;\n" +
            "  top: 50%;\n" +
            "  left: 50%;\n" +
            "  transform: translate(-50%, -50%);\n" +
            "}\n"+
            "\n" +
            "\n" +
            "    </style>";
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println(style);
        out.println("<div>");
        out.println("<h1>" + message + "</h1>");
        out.println("<form action=\"hello-servlet\" method=\"post\">");
        out.println("<label for=\"autonummer\">Autonummer:</label>");
        out.println("<input type=\"text\" id=\"autonummer\" name=\"autonummer\" required>");
        out.println("<br><br>");
        out.println("<label for=\"ticketNumber\">Ticket Number:</label>");
        out.println("<input type=\"text\" id=\"ticketNumber\" name=\"ticketNumber\" required>");
        out.println("<br><br>");
        out.println("<input type=\"submit\" value=\"Get Ticket Price\">");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ticketNumber = request.getParameter("ticketNumber");
        String creditCardNumber = request.getParameter("creditCardNumber");

        Ticket ticket = TicketResponse.tickets.get(Integer.parseInt(ticketNumber) - 1);

        try {
            if (request.getParameter("autonummer").equals(ticket.getAutoNummer())) {
                double price = ticket.bezahlen();


                out.println("<html><body>");
                out.println(style);
                out.println("<h1>Ticket Price</h1>");
                out.println("<p>The price for ticket number " + ticketNumber + " is " +ticket.getPrice()+"Euro" + "</p>");

                // Formular fÃ¼r die Auswahl der Zahlungsmethode
                out.println("<form action=\"hello-servlet\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"ticketNumber\" value=\"" + ticketNumber + "\">");
                out.println("<input type=\"hidden\" name=\"autonummer\" value=\"" + ticket.getAutoNummer()+ "\">");
                out.println("<label for=\"paymentMethod\">Choose Payment Method:</label>");
                out.println("<select id=\"paymentMethod\" name=\"paymentMethod\">");
                out.println("<option value=\"Credit Card\">Credit Card</option>");
                out.println("<option value=\"Bar Geld\">Bar Geld</option>");
                out.println("</select>");
                out.println("<br><br>");
                out.println("<input type=\"submit\" value=\"Proceed to Payment\">");
                out.println("</form>");

                out.println("</body></html>");
                // Speichern des Preiswerts in der Sitzung

            } else {

                out.println("Registration number is incorrect!");
            }
        }
        catch (IllegalStateException e) {
            out.println("<p> You already paid your Ticket</p>");
        }
        String paymentMethod = request.getParameter("paymentMethod");
        if (paymentMethod != null) {
            if (paymentMethod.equals("Credit Card")) {
                // Weiterleitung zur Payment.jsp
                response.sendRedirect("Payment.jsp?ticketNumber=" + ticketNumber + "&autonummer=" + ticket.getAutoNummer() +  "&paymentMethod=" + paymentMethod);
            } else if (paymentMethod.equals("Bar Geld")) {

                response.sendRedirect("CashPaymentSuccess.jsp?ticketNumber=" + ticketNumber + "&autonummer=" + ticket.getAutoNummer() + "&price=" + ticket.getPrice());

            }
        }

    }


    public void destroy() {
    }
}


