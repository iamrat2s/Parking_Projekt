package com.example.parhausprj;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatbotTest {
    @Test
    public void testGetBotReply() {
        // Test case 1: Greeting message
        String userMessage1 = "Hello";
        String expectedReply1 = "Hello! How can I assist you?" +
                "1. Complaint" +
                "2. Lost ticket" +
                "3. Payment method" +
                "4. Contact";
        String actualReply1 = Chatbot.getBotReply(userMessage1);
        Assertions.assertEquals("Hello! How can I assist you?<br><br>1. Complaint<br><br>2. Lost ticket<br><br>3. Payment method<br><br>4. Contact", actualReply1);

        // Test case 2: Opening hours
        String userMessage2 = "1";
        String expectedReply2 = "Sad to hear that a customer has filed a complaint against us. We would like to inform you that there is an option available on our homepage where you can submit your complaint. Our support team will respond to it as quickly as possible.";
        String actualReply2 = Chatbot.getBotReply(userMessage2);
        Assertions.assertEquals(expectedReply2, actualReply2);

        // Test case 3: Lost ticket
        String userMessage3 = "2";
        String expectedReply3 = "Sad to hear that you lost your ticket. You can go to the homepage and select 'Lost Ticket'. Enter your car's license plate number, and you will receive your ticket number again. Please note that in case of a lost ticket, an additional fee of 30 euros will be charged";
        String actualReply3 = Chatbot.getBotReply(userMessage3);
        Assertions.assertEquals(expectedReply3, actualReply3);

        // Test case 4: Payment method
        String userMessage4 = "3";
        String expectedReply4 = "We accept payments via credit card, and cash.";
        String actualReply4 = Chatbot.getBotReply(userMessage4);
        Assertions.assertEquals(expectedReply4, actualReply4);

        // Test case 5: Contact information
        String userMessage5 = "4";
        String expectedReply5 = "You can reach us by phone at 0123456789 or by email at Parkhaus404@gmail.com.";
        String actualReply5 = Chatbot.getBotReply(userMessage5);
        Assertions.assertEquals(expectedReply5, actualReply5);
    }
}