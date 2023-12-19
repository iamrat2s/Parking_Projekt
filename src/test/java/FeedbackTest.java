package com.example.parhausprj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackTest {

    @Test
    public void testGetters() {
        String name = "John Doe";
        String email = "johndoe@example.com";
        String message = "This is a test message";
        String subject = "Test Subject";
        String time = "2023-06-26 12:00:00";

        Feedback feedback = new Feedback(name, email, message, time, subject);

        Assertions.assertEquals(name, feedback.getName());
        Assertions.assertEquals(email, feedback.getEmail());
        Assertions.assertEquals(message, feedback.getMessage());
        Assertions.assertEquals(subject, feedback.getSubject());
        Assertions.assertEquals(time, feedback.getTime());
    }
}
