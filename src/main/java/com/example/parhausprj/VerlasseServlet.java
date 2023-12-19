package com.example.parhausprj;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VerlasseServlet", value = "/verlasse-servlet")
public class VerlasseServlet extends HttpServlet{
    String message = "Leave park house";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println(HelloServlet.style);
        out.println("<div style=\"text-align:center;\">");
        out.println("<h1>" + message + "</h1>");
        out.println("<form action=\"verlasse-servlet\" method=\"post\">");
        out.println("<label for=\"autonummer\">Autonummer:</label>");
        out.println("<input type=\"text\" id=\"autonummer\" name=\"autonummer\" required>");
        out.println("<br><br>");
        out.println("<input type=\"submit\" value=\"Leave\">");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            int ticketnummer=0;
            String autonummer = request.getParameter("autonummer");

            // Erstellen Sie eine Instanz der Ticket-Klasse und legen Sie die Werte für das Ticket fest
            //  Ticket ticket = new Ticket(1234, "ABC-123", 10.0);
            for(Ticket i:TicketResponse.tickets){

                if((i.getAutoNummer().equals(autonummer) && i.isOut() == false)) {
                    i.verlasse();
                    if ( i.getState() instanceof com.example.parhausprj.Ausgefahren) {
                        ticketnummer=i.getTicketNummer();
                        out.println("<html><body>");
                        out.println("<h1>Successful!</h1>");
                        out.println("<p>Ticket Number: " +ticketnummer+ "</p>");
                        out.println("<p>Amount Charged: " + (i.getPrice()+i.getVerlustGeb()) + "<span>&#8364;</span> </p>");
                        out.println("</body></html>");
                    }
                    if ((i.getState() instanceof com.example.parhausprj.Nachzahlen)) {
                        ticketnummer=i.getTicketNummer();
                        out.println("You stayed for too long pay the diff.");
                    }

                }

            }
            if(ticketnummer==0){

                out.println("Go get a ticket first");
            }
            out.println("<button onclick=\"window.location.href='index.jsp'\">Back to Home</button>");
        } catch ( Exception e) {
            out.println(e.toString());
        }
    }
}
