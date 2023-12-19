package com.example.parhausprj;
import com.example.parhausprj.Feedback;
import java.util.List;
import java.util.stream.Collectors;

public class FeedbackTableGenerator {
    public static String generateComplaintTable(List<Feedback> feedbacks) {
        List<Feedback> complaints = feedbacks.stream()
                .filter(feedback -> feedback.getSubject().equals("complaint"))
                .collect(Collectors.toList());

        return generateTable(complaints);
    }

    public static String generateFeedbackTable(List<Feedback> feedbacks) {
        List<Feedback> feedbackList = feedbacks.stream()
                .filter(feedback -> feedback.getSubject().equals("feedback"))
                .collect(Collectors.toList());

        return generateTable(feedbackList);
    }

    private static String generateTable(List<Feedback> feedbacks) {
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("<table>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>Email</th>")
                .append("<th>Time</th>")
                .append("<th>Nachricht</th>")
                .append("</tr>");

        feedbacks.forEach(feedback -> tableBuilder
                .append("<tr>")
                .append("<td>").append(feedback.getName()).append("</td>")
                .append("<td>").append(feedback.getEmail()).append("</td>")
                .append("<td>").append(feedback.getTime()).append("</td>")
                .append("<td>").append(feedback.getMessage()).append("</td>")
                .append("</tr>")
        );

        tableBuilder.append("</table>");

        return tableBuilder.toString();
    }
}
