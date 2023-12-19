import com.example.parhausprj.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsTest {
    double expectedPaidAvg = (10. +15.) / 2.0;
    double expectedPaidPercentage = 100.0;
    double expectedVerlustGAvg = (5.0 + 8.0) / 2.0;
    double expectedTimeAvg = (2.0) / 2.0;
    List<Ticket> tickets;
    Ticket ticket1;
    Ticket ticket2;
    @BeforeEach
    public void testTicketStatistics() {
        // Create a list of Ticket objects
        tickets = new ArrayList<>();
        ticket1 = new Ticket(1, "AA-AA-0000", 10);
        ticket1.setVerlustGeb(5.0);
        myLocalDate.timewarp(60);
        ticket1.bezahlen();
        ticket1.verlasse();
        tickets.add(ticket1);

        ticket2 = new Ticket(2, "AA-AA-0000", 15);
        ticket2.setVerlustGeb(8.0);
        myLocalDate.timewarp(60);
        ticket2.bezahlen();
        ticket2.verlasse();
        tickets.add(ticket2);
    }
    @Test
    public void testPaidAvg() {
        double paidAvg = tickets.stream()
                .filter(x -> x.austrittszeit != null).mapToDouble(Ticket::getPrice).average().orElse(0.0);
        assertEquals(expectedPaidAvg, paidAvg, 0.0001);
    }
    @Test
    public void testPaidPerc() {
        double paidPercentage = (double) tickets.stream()
                .filter(x -> x.austrittszeit != null)
                .collect(Collectors.toList()).size() / tickets.size() * 100.0;
        assertEquals(expectedPaidPercentage, paidPercentage, 0.0001);
    }
    @Test
    public void testVerlustAvg() {
        double verlustGAvg = tickets.stream()
                .filter(x -> x.austrittszeit != null)
                .collect(Collectors.toList()).stream().mapToDouble(Ticket::getVerlustGeb).average().orElse(0.0);
        assertEquals(expectedVerlustGAvg, verlustGAvg, 0.0001);
    }
    @Test
    public void testTimeAVG() {
        double timeAvg = tickets.stream()
                .filter(x -> x.austrittszeit != null).mapToDouble(x ->
                        Duration.between(x.getEintrittszeit(), x.getBezahlzeit()).toHours() +
                                (double)(Duration.between(x.getEintrittszeit(), x.getBezahlzeit()).toMinutes() % 60) / 60.0
                ).average().orElse(0.0);




        assertEquals(expectedTimeAvg, timeAvg, 0.0001);
    }





    }

