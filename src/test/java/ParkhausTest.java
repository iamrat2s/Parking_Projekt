package com.example.parhausprj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausTest {
    private Parkhaus parkhaus;

    @BeforeEach
    public void setUp() {
        LocalTime currentTime = LocalTime.of(12, 0, 0);
        parkhaus = new Parkhaus(currentTime);
    }

    @Test
    @DisplayName("Test getCurrentTime() returns the correct current time")
    void getCurrentTime() {
        LocalTime expectedTime = LocalTime.of(12, 0, 0);
        LocalTime currentTime = parkhaus.getCurrentTime();

        assertEquals(expectedTime, currentTime);
    }

    @Test
    @DisplayName("Test setCurrentTime() sets the current time correctly")
    void setCurrentTime() {
        LocalTime newTime = LocalTime.of(14, 30, 0);
        parkhaus.setCurrentTime(newTime);
        LocalTime currentTime = parkhaus.getCurrentTime();

        assertEquals(newTime, currentTime);
    }
}