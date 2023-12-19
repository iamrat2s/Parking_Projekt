package com.example.parhausprj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FeedbackTableGeneratorTest {

    @Test
    public void testGenerateComplaintTable() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback("John Doe", "johndoe@example.com", "This is a complaint message", "2023-06-26 12:00:00", "complaint"));
        feedbacks.add(new Feedback("Jane Smith", "janesmith@example.com", "Another complaint message", "2023-06-26 13:00:00", "complaint"));
        feedbacks.add(new Feedback("Mike Johnson", "mikejohnson@example.com", "Yet another complaint", "2023-06-26 14:00:00", "feedback"));

        String expectedTable = "<table><tr><th>Name</th><th>Email</th><th>Time</th><th>Nachricht</th></tr>" +
                "<tr><td>John Doe</td><td>johndoe@example.com</td><td>2023-06-26 12:00:00</td><td>This is a complaint message</td></tr>" +
                "<tr><td>Jane Smith</td><td>janesmith@example.com</td><td>2023-06-26 13:00:00</td><td>Another complaint message</td></tr>" +
                "</table>";

        String actualTable = FeedbackTableGenerator.generateComplaintTable(feedbacks);

        Assertions.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testGenerateFeedbackTable() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback("John Doe", "johndoe@example.com", "This is a feedback message", "2023-06-26 12:00:00", "feedback"));
        feedbacks.add(new Feedback("Jane Smith", "janesmith@example.com", "Another feedback message", "2023-06-26 13:00:00", "complaint"));
        feedbacks.add(new Feedback("Mike Johnson", "mikejohnson@example.com", "Yet another feedback", "2023-06-26 14:00:00", "feedback"));

        String expectedTable = "<table><tr><th>Name</th><th>Email</th><th>Time</th><th>Nachricht</th></tr>" +
                "<tr><td>John Doe</td><td>johndoe@example.com</td><td>2023-06-26 12:00:00</td><td>This is a feedback message</td></tr>" +
                "<tr><td>Mike Johnson</td><td>mikejohnson@example.com</td><td>2023-06-26 14:00:00</td><td>Yet another feedback</td></tr>" +
                "</table>";

        String actualTable = FeedbackTableGenerator.generateFeedbackTable(feedbacks);

        Assertions.assertEquals(expectedTable, actualTable);
    }
}
