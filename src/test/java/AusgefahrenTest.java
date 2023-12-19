package com.example.parhausprj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AusgefahrenTest {
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket(1, "AA-AA-0000", 10.0);
    }

    @Test
    @DisplayName("Test bezahle() throws IllegalStateException")
    void bezahle() {
        Ausgefahren ausgefahren = new Ausgefahren();

        assertThrows(IllegalStateException.class, () -> ausgefahren.bezahle(ticket));
    }

    @Test
    @DisplayName("Test verliere() throws IllegalStateException")
    void verliere() {
        Ausgefahren ausgefahren = new Ausgefahren();

        assertThrows(IllegalStateException.class, () -> ausgefahren.verliere(ticket));
    }

    @Test
    @DisplayName("Test verlasse() throws IllegalStateException")
    void verlasse() {
        Ausgefahren ausgefahren = new Ausgefahren();

        assertThrows(IllegalStateException.class, () -> ausgefahren.verlasse(ticket));
    }
}