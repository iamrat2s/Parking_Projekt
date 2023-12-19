import com.example.parhausprj.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TicketBezahltTest {

    private Ticket ticket = new Ticket(1,"AA-AA-0000",10);



    @Test
    @DisplayName("Test TicketBezahlt state - bezahle()")
    void testTicketBezahltBezahle() {
        TicketBezahlt ticketBezahlt = new TicketBezahlt();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            ticketBezahlt.bezahle(ticket);
        });
    }

    @Test
    @DisplayName("Test TicketBezahlt state - verliere()")
    void testTicketBezahltVerliere() {
        TicketBezahlt ticketBezahlt = new TicketBezahlt();

        ticket.setVerlustGeb(0.0);
        State nextState = ticketBezahlt.verliere(ticket);

        Assertions.assertTrue(nextState instanceof TicketVerloren);
        Assertions.assertEquals(30.0, ticket.getVerlustGeb());
    }

    @Test
    @DisplayName("Test TicketBezahlt state - verlasse()")
    void testTicketBezahltVerlasse() {
        TicketBezahlt ticketBezahlt = new TicketBezahlt();
        ticket.setBezahlzeit(myLocalDate.myCurrentTime()); // Set the bezahlzeit field with a valid date
        ticket.setOut(false);
        int initialFreePlaces = Offnungzeitenservlet.Freeplaces;
        Parkhauss.lots[ticket.getPlace()] = String.valueOf(ticket);

        State nextState = ticketBezahlt.verlasse(ticket);

        Assertions.assertTrue(nextState instanceof Ausgefahren);
        Assertions.assertTrue(ticket.isOut());
        Assertions.assertNull(Parkhauss.lots[ticket.getPlace()]);
        Assertions.assertEquals(initialFreePlaces + 1, Offnungzeitenservlet.Freeplaces);
    }
}