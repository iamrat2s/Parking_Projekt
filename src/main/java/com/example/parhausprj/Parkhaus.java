package com.example.parhausprj;



import java.time.Duration;
import java.time.LocalTime;

public class Parkhaus {
    static public LocalTime currentTime;

    public Parkhaus(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }
}
