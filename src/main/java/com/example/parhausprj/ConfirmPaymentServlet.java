package com.example.parhausprj;

import com.example.parhausprj.Ticket;
import com.example.parhausprj.TicketResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "confirmPaymentServlet", value = "/confirm-payment")
public class ConfirmPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ticketNumber = request.getParameter("ticketNumber");

        String autonummer = request.getParameter("autonummer");

        Ticket ticket = TicketResponse.tickets.get(Integer.parseInt(ticketNumber) - 1);

        out.println("<html><body>");
        out.println("<center>");
        out.println("<h1>Payment Successful</h1>");
        out.println("<p>Ticket Number: " + ticketNumber + "</p>");
        out.println("<p>Autonummer: " + autonummer + "</p>");
        out.println("<p>Eintrittzeit: " + ticket.eintrittszeit+ "</p>");
        out.println("<p>Bezahlzeit: " + ticket.bezahlzeit + "</p>");
        out.println("<p>Amount Charged: " + ticket.getPrice() + "<span>&#8364;</span> </p>");
        out.println("<button onclick=\"window.location.href='index.jsp'\">Back to Home</button>");
        out.println("<button onclick=\"printReceipt()\">Print Receipt</button>");
        out.println("</center>");
        out.println("</body></html>");
        out.println("<script>");
        out.println("function printReceipt() {");
        out.println("  window.print();");
        out.println("}");
        out.println("</script>");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        doPost(request, response);
    }

}