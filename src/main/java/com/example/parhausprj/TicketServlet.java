package com.example.parhausprj;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "TicketServlet", value = "/ticket-servlet")
public class TicketServlet extends HttpServlet {
    private String message = "Get a Ticket";
    public double price = 10.0;


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(HelloServlet.style);
        out.println("<div style=\"text-align:center;\">");
        out.println("<h1>" + message + "</h1>");
        out.println("<form action=\"ticket-response \"method=\"post\">" +
                "<label for=\"matrikulNummer\">Car registration number:</label>" +
                "<input type=\"text\" pattern=\"[A-Za-z]{2}-[A-Za-z]{2}-[0-9]{4}\" " +
                "id=\"matrikulNummer\" title=\"i.e BB-BB-0000\" name=\"matrikulNummer\"\" required><br><br>"+
                "<input type=\"submit\"value=\"Submit\"> " +
                "</form>" +
                "<a href=\"index.jsp\"><button type=\"Go Back\">Back to Home</button></a>");
        out.println("</div>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
