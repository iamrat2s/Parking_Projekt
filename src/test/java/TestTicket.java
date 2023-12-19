import com.example.parhausprj.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TestTicket {
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        ticket = new Ticket(1, "AA-AA-0000", 10.0);
    }
    @Test
    @DisplayName("Test setPlace() : Should set the place of ticket")
    public void testSetPlace() {
        ticket.setPlace(2);
        Assertions.assertEquals(2,ticket.getPlace());
    }
    @Test
    @DisplayName("Test isOut(): Should return the value of 'out' flag")
    public void testIsOut() {
        boolean initialOutValue = ticket.isOut();
        Assertions.assertFalse(initialOutValue); // Initially, 'out' should be false

        ticket.setOut(true);
        boolean updatedOutValue = ticket.isOut();
        Assertions.assertTrue(updatedOutValue); // After setting 'out' to true, it should be true
    }

    // Test case for setOut() method
    @Test
    @DisplayName("Test setOut(): Should set the value of 'out' flag")
    public void testSetOut() {
        ticket.setOut(false);
        boolean initialOutValue = ticket.isOut();
        Assertions.assertFalse(initialOutValue); // After setting 'out' to false, it should be false

        ticket.setOut(true);
        boolean updatedOutValue = ticket.isOut();
        Assertions.assertTrue(updatedOutValue); // After setting 'out' to true, it should be true
    }

    @Test
    @DisplayName("Test getTicketNummer(): Should return the ticket number")
    public void testGetTicketNummer() {
        Assertions.assertEquals(1, ticket.getTicketNummer());
    }

    @Test
    @DisplayName("Test getAutoNummer(): Should return the car number")
    public void testGetAutoNummer() {
        Assertions.assertEquals("AA-AA-0000", ticket.getAutoNummer());
    }

    @Test
    @DisplayName("Test getTicketPrice(): Should return the ticket price")
    public void testGetTicketPrice() {
        ticket.setTicketPrice(2.99);
        Assertions.assertEquals(2.99, ticket.getTicketPrice());
    }

    @Test
    @DisplayName("Test setTicketPrice(): Should set the ticket price")
    public void testSetTicketPrice() {
        ticket.setTicketPrice(15.0);
        Assertions.assertEquals(15.0, ticket.getTicketPrice());
    }

    @Test
    @DisplayName("Test ticketZiehen(): Should set the entry time and change the ticket state to 'TicketGezogen'")
    public void testTicketZiehen() {
        LocalDateTime eintrittszeit = ticket.ticketZiehen();
        Assertions.assertNotNull(eintrittszeit);
        Assertions.assertTrue(ticket.getState() instanceof TicketGezogen);
    }

    @Test
    @DisplayName("Test ticketValidieren(): Should validate the ticket and return the parking duration in hours")
    public void testTicketValidieren() {
        ticket.ticketZiehen();
        double parkdauerInStunden = ticket.ticketValidieren();
        Assertions.assertTrue(parkdauerInStunden >= 0);
    }

    @Test
    @DisplayName("Test bezahlen(): Should pay the ticket and change the ticket state to 'TicketBezahlt'")
    public void testBezahlen() {
        ticket.ticketZiehen();
        ticket.bezahlen();
        Assertions.assertTrue(ticket.getState() instanceof TicketBezahlt);
    }

    @Test
    @DisplayName("Test verloren(): Should mark the ticket as lost and change the ticket state to 'TicketVerloren'")
    public void testVerloren() {
        ticket.verloren();
        Assertions.assertTrue(ticket.getState() instanceof TicketVerloren);
    }

    @Test
    @DisplayName("Test verlasse(): Should mark the ticket as exited and change the ticket state to 'Ausgefahren'")
    public void testVerlasse() {
        ticket.ticketZiehen();
        ticket.bezahlen();
        ticket.verlasse();
        Assertions.assertTrue(ticket.getState() instanceof Ausgefahren);
    }

    @Test
    @DisplayName("Test getAllTickets(): Should return a list of all tickets")
    public void testGetAllTickets() {
        List<Ticket> allTickets = Ticket.getAllTickets();
        Assertions.assertNotNull(allTickets);
    }

    @Test
    @DisplayName("Test getSortedTicketsByPriceDescending(): Should return a list of tickets sorted by price in descending order")
    public void testGetSortedTicketsByPriceDescending() {
        List<Ticket> sortedTickets = Ticket.getSortedTicketsByPriceDescending();
        Assertions.assertNotNull(sortedTickets);
    }

    @Test
    @DisplayName("Test getTicketsByState(): Should return a list of tickets filtered by the state 'TicketVerloren'")
    public void testGetTicketsByState() {
        List<Ticket> ticketsByState = Ticket.getTicketsByState();
        Assertions.assertNotNull(ticketsByState);
    }

    @Test
    @DisplayName("Test getPaidTickets(): Should return a list of tickets that have been paid")
    public void testGetPaidTickets() {
        List<Ticket> paidTickets = Ticket.getPaidTickets();
        Assertions.assertNotNull(paidTickets);
    }

    @Test
    @DisplayName("Test getUnpaidTickets(): Should return a list of tickets that have not been paid")
    public void testGetUnpaidTickets() {
        List<Ticket> unpaidTickets = Ticket.getunPaidTickets();
        Assertions.assertNotNull(unpaidTickets);
    }
    @Test
    @DisplayName("Test getVerlustGeb(): Should return the value of 'verlustGeb'")
    public void testGetVerlustGeb() {
        ticket.setVerlustGeb(5.0);
        double verlustGeb = ticket.getVerlustGeb();
        Assertions.assertEquals(5.0, verlustGeb);
    }

    // Test case for getEintrittszeit() method
    @Test
    @DisplayName("Test getEintrittszeit(): Should return the value of 'eintrittszeit'")
    public void testGetEintrittszeit() {
        LocalDateTime eintrittszeit = myLocalDate.myCurrentTime();
        ticket.eintrittszeit = eintrittszeit;
        LocalDateTime returnedEintrittszeit = ticket.getEintrittszeit();
        Assertions.assertEquals(eintrittszeit, returnedEintrittszeit);
    }

    // Test case for getBezahlzeit() method
    @Test
    @DisplayName("Test getBezahlzeit(): Should return the value of 'bezahlzeit'")
    public void testGetBezahlzeit() {
        LocalDateTime bezahlzeit = myLocalDate.myCurrentTime();
        ticket.bezahlzeit = bezahlzeit;
        LocalDateTime returnedBezahlzeit = ticket.getBezahlzeit();
        Assertions.assertEquals(bezahlzeit, returnedBezahlzeit);
    }

    // Test case for getState() and setState() methods
    @Test
    @DisplayName("Test getState() and setState(): Should set and return the state of the ticket")
    public void testGetAndSetState() {
        State initialState = ticket.getState();
        Assertions.assertNotNull(initialState);

        State newState = new TicketGezogen();
        ticket.setState(newState);
        State updatedState = ticket.getState();
        Assertions.assertEquals(newState, updatedState);
    }

    // Test case for rounded() method
    @Test
    @DisplayName("Test rounded(): Should round the given value to two decimal places")
    public void testRounded() {
        double value = 3.14159;
        double roundedValue = ticket.rounded(value);
        Assertions.assertEquals(3.14, roundedValue);
    }

    // Test case for setPrice() and getPrice() methods
    @Test
    @DisplayName("Test setPrice() and getPrice(): Should set and return the price of the ticket")
    public void testSetAndGetPrice() {
        ticket.setPrice(12.99);
        double price = ticket.getPrice();
        Assertions.assertEquals(12.99, price);
    }

    // Test case for setVerlustGeb() and getVerlustGeb() methods
    @Test
    @DisplayName("Test setVerlustGeb() and getVerlustGeb(): Should set and return the verlustGeb of the ticket")
    public void testSetAndGetVerlustGeb() {
        ticket.setVerlustGeb(5.0);
        double verlustGeb = ticket.getVerlustGeb();
        Assertions.assertEquals(5.0, verlustGeb);
    }

    // Test case for setNachzahlung() and getNachzahlung() methods
    @Test
    @DisplayName("Test setNachzahlung() and getNachzahlung(): Should set and return the nachzahlung of the ticket")
    public void testSetAndGetNachzahlung() {
        ticket.setNachzahlung(2.0);
        double nachzahlung = ticket.getNachzahlung();
        Assertions.assertEquals(2.0, nachzahlung);
    }

    // Test case for setAustrittszeit() method
    @Test
    @DisplayName("Test setAustrittszeit(): Should set the value of 'austrittszeit'")
    public void testSetAustrittszeit() {
        LocalDateTime austrittszeit = myLocalDate.myCurrentTime();
        ticket.setAustrittszeit(austrittszeit);
        LocalDateTime returnedAustrittszeit = ticket.austrittszeit;
        Assertions.assertEquals(austrittszeit, returnedAustrittszeit);
    }
    @Test
    @DisplayName("Test setTicketPricegr0() : Price must be positive.")
    public void testSetTicketPricegr0() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->ticket.setTicketPrice(-12.0));
    }
    @Test
    @DisplayName("Throws Exceptions if there is no free places .")
    public void testTicketziehennfree() {
        int tmp = Offnungzeitenservlet.Freeplaces;
        Offnungzeitenservlet.Freeplaces = 0 ;
        Assertions.assertThrows(IllegalStateException.class, () ->ticket.ticketZiehen());
        Offnungzeitenservlet.Freeplaces = tmp;
    }
    @RepeatedTest(3)
    @DisplayName("test timewrap accuracy.")
    void ticketValidieren() {
        ticket.ticketZiehen();
        // Das Ticket nach einer Stunde validieren
        myLocalDate.timewarp(60);
        ticket.bezahlen();
        double result = ticket.getPrice();
        double expected = 10.0;
        Assertions.assertEquals(result,expected);
        myLocalDate.timewarp(-60);

    }
}
