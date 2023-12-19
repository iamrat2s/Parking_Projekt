package com.example.parhausprj;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ZeitServlet", value = "/zeit-servlet")
public class Offnungzeitenservlet extends HttpServlet {
    private String message;

    private static final long serialVersionUID = 1L;

    private ParkhausIF parkhaus = new Parkhauss();

    public static int Freeplaces= 200;
    public  static String openingHours= "Mo-Fr 9-18, Sa-Su 10-16";
    public void init() {
        parkhaus = new Parkhauss();

        //parkhaus.setOpeningHours("Mo-Fr 9-18, Sa-Su 10-16");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        openingHours= request.getParameter("openingHours");

        request.setAttribute("openingHours", openingHours);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        //dispatcher.forward(request, response);

        response.sendRedirect("index.jsp");



    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        parkhaus.setOpeningHours(request.getParameter("openingHours")); // Setzen Sie die Öffnungszeiten hier

        String openingHours = parkhaus.getOpeningHours();
        request.setAttribute("openingHours", openingHours);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1><center>Welcome to our parking </center></h1>");
        out.println("<h2><center>opening hours: " + openingHours + "</center></h2>");

        // Formular-Button hinzufügen
        out.println("<form method=\"post\" action=\"zeit-servlet\">");
        out.println("<input type=\"text\" name=\"openingHours\" placeholder=\"New opening hours\">");
        out.println("<button type=\"submit\">Change opening hours</button>");
        out.println("</form>");

        out.println("</body></html>");
    }



    public void destroy() {
    }
}
