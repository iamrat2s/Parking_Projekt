package com.example.parhausprj;

public class Chatbot {
    public static String getBotReply(String userMessage) {
        String question = userMessage.toLowerCase();
        String botReply = "I'm sorry, I cannot answer your question. Please contact our Support team 0123456789 ";

        if (question.contains("hello") || question.contains("hi")) {
            String options = "1. Complaint<br><br>" +
                    "2. Lost ticket<br><br>" +
                    "3. Payment method<br><br>" +
                    "4. Contact";

            botReply = "Hello! How can I assist you?<br><br>" + options;
        } else if (question.contains("1") || question.contains("opening hours")) {
            botReply = "Sad to hear that a customer has filed a complaint against us. We would like to inform you that there is an option available on our homepage where you can submit your complaint. Our support team will respond to it as quickly as possible.";
        } else if (question.contains("2")) {
            botReply = "Sad to hear that you lost your ticket. You can go to the homepage and select 'Lost Ticket'. Enter your car's license plate number, and you will receive your ticket number again. Please note that in case of a lost ticket, an additional fee of 30 euros will be charged";
        } else if (question.contains("3")) {
            botReply = "We accept payments via credit card, and cash.";
        } else if (question.contains("4")) {
            botReply = "You can reach us by phone at 0123456789 or by email at Parkhaus404@gmail.com.";
        }

        return botReply;
    }


}
