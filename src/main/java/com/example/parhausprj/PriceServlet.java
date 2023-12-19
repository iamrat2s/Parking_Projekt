package com.example.parhausprj;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "priceServlet", value = "/price-servlet")
public class PriceServlet extends HttpServlet  {
    public static double ticketPrice = 2.99 ;

    @Override
    public void init()  { }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

        response.setContentType("text/html");
        String ticketPriceParam = request.getParameter("ticketPrice");
        if (ticketPriceParam != null) {
            ticketPrice = Double.parseDouble(ticketPriceParam);
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException{

         ticketPrice = Double.parseDouble(request.getParameter("ticketPrice"));

        request.setAttribute("ticketPrice", ticketPrice);
        response.sendRedirect("index.jsp");

    }

    public void destroy() {
    }
}
