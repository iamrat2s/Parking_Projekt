package com.example.parhausprj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "platzServlet", value = "/platz-servlet")
public class SpaceServlet extends HttpServlet {

    public static Parkplatz parkplatz = new Parkplatz(200);

    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display a form where the user can enter the number of the parking space they want to reserve
        request.getRequestDispatcher("/reserve.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String spaceNumberStr = request.getParameter("spaceNumber");
        int spaceNumber = Integer.parseInt(spaceNumberStr);
        // Attempt to reserve the parking space
        Parkplatz.ParkingSpace parkingSpace = parkplatz.reserveParkingSpace(spaceNumber + 1, request.getParameter("autonummer"));
        // Display the result to the user
        if (parkingSpace != null) {
            response.setContentType("text/html");
            response.getWriter().println("The Parking place number " + " " + (parkingSpace.getNumber() - 1) + " " + "is now reserved for you");
            response.getWriter().println("<br><br><br>");
            response.getWriter().println("<a href=\"index.jsp\"><button type=\"button\">Back to Home</button></a>");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("This parking place is already reserved please choose another Place");
            response.getWriter().println("<br><br><br>");
            response.getWriter().println("<a href=\"index.jsp\"><button type=\"button\">Back to Home</button></a>");
        }


    }
}

