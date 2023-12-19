import com.example.parhausprj.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class TestNachzahlen {
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket(1, "AA-AA-0000", 10.0);
    }

    @Test
    @DisplayName("Test Bezahle() : Should pay extra fees if the customer stays longer than 30 min")

    public void testBezahle() {

        /*ticket.setBezahlzeit(myLocalDate.myCurrentTime());
        ticket.setAustrittszeit(ticket.getBezahlzeit().plusHours(1)); // Set austrittszeit 1 second after bezahlzeit

        Nachzahlen state = new Nachzahlen();
        State nextState = state.bezahle(ticket);*/
        ticket.ticketZiehen();
        ticket.bezahlen();
        myLocalDate.timewarp(50);
        ticket.verlasse();
        System.out.println(ticket.getState());
        Assertions.assertTrue(ticket.getState() instanceof Nachzahlen);
    }

    @Test
    @DisplayName("Test Verliere() : Should pay the extra fees for loosing the ticket")

    public void testVerliere() {

        double initialVerlustGeb = ticket.getVerlustGeb();

        Nachzahlen state = new Nachzahlen();
        State nextState = state.verliere(ticket);

        double expectedVerlustGeb = initialVerlustGeb + 30.0;
        Assertions.assertEquals(expectedVerlustGeb, ticket.getVerlustGeb(), 0.001);
        Assertions.assertTrue(nextState instanceof TicketVerloren);
    }

    @Test
    @DisplayName("Test Verlasse() : Should throw an exception to pay the extra fees befor leaving")
    public void testVerlasse() {


        Nachzahlen state = new Nachzahlen();

        Assertions.assertThrows(IllegalStateException.class, () -> state.verlasse(ticket));
    }


}