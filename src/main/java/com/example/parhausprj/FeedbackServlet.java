package com.example.parhausprj;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback-servlet")
public class FeedbackServlet extends HttpServlet {
    private List<Feedback> feedbackList;

    @Override
    public void init() throws ServletException {
        super.init();
        feedbackList = new ArrayList<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String time = request.getParameter("visitTime");
        String subject = request.getParameter("subject");

        Feedback feedback = new Feedback(name, email, message,time,subject);
        feedbackList.add(feedback);

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("feedbacks.jsp").forward(request, response);
    }
}
