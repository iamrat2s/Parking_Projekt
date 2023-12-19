package com.example.parhausprj;

import java.time.LocalDateTime;

public class Feedback {
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getTime() {
        return time;
    }
    public String getMessage() {
        return message;
    }

    private String name;
    private String email;
    private String message;
    private String subject;
    private  String time;
    public String getSubject() {
        return subject;
    }
    public Feedback(String name, String email, String message,String time,String subject) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.subject = subject;
        this.time = time;
    }

    // Getter und Setter für die Variablen
}
