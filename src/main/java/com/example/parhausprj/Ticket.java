package com.example.parhausprj;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
public class Ticket {
    int ticketNummer ;
    String autoNummer;
    public double ticketPrice;
    public LocalDateTime eintrittszeit;
    public LocalDateTime austrittszeit;
    public LocalDateTime bezahlzeit;
    double verlustGeb= 0;
    double nachzahlung = 0;
    boolean bezahlt = false ;
    int place ;
    private static List<Ticket> allTickets = new ArrayList<>();
    boolean out = false;
    static double totalSales = 0.0;
    double price = 0;
    State state = new TicketGezogen();

    public String getAutoNummer() {
        return autoNummer;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }


    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public int getTicketNummer() {
        return ticketNummer;
    }

    public Ticket(int ticketNummer, String autoNummer, double ticketPrice) {
        this.ticketPrice = ticketPrice;
        this.ticketNummer = ticketNummer;
        this.autoNummer = autoNummer;
        this.state = new TicketGezogen();
        this.ticketZiehen();
        allTickets.add(this);
    }
    public static List<Ticket> getAllTickets() {
        return allTickets;
    }

    public void setTicketPrice(double ticketPrice) {
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Ticketpreis darf nicht negativ sein!");
        }
        this.ticketPrice = ticketPrice;
    }



    public double bezahlen() {

        this.state = state.bezahle(this);
        totalSales+=rounded(price);
        return price;
    }


    public LocalDateTime ticketZiehen() throws IllegalStateException {
        boolean reserved = false;
        if(Offnungzeitenservlet.Freeplaces==0){
            throw new IllegalStateException("No free places anymore");
        }
        eintrittszeit = myLocalDate.myCurrentTime();
        for (int i = 0; i<200 ; i++){
            try {
                if (SpaceServlet.parkplatz.getSpace(i).getAutonummer().equals(this.autoNummer) ) {
                    reserved = true;
                }
            } catch (Exception e) {

            }

        }
        if (!reserved) {
            Offnungzeitenservlet.Freeplaces--;
        }
        return eintrittszeit;

    }


    public double ticketValidieren() {
        if (eintrittszeit == null) {
            throw new IllegalStateException("Eintrittszeit wurde nicht gesetzt.");
        }
        bezahlzeit = myLocalDate.myCurrentTime();
        double parkdauerInStunden = Duration.between(eintrittszeit,bezahlzeit).toHours()+ (double)(Duration.between(eintrittszeit,bezahlzeit).toMinutes()%60)/60.0;
        System.out.println(parkdauerInStunden);
        //double parkdauerInStunden = (parkdauerInMillisekunden / (60.0 * 60.0 * 1000.0)); // Umrechnung von Millisekunden in Stunden
        // weitere Schritte, um das Ticket zu validieren und den Preis zu berechnen
        return parkdauerInStunden;
    }



    public void verloren() {
        this.state = state.verliere(this);
    }
    public void verlasse() {
        this.state = state.verlasse(this);
    }
    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public double getVerlustGeb() {
        return verlustGeb;
    }

    public LocalDateTime getEintrittszeit() {
        return eintrittszeit;
    }
    public LocalDateTime getBezahlzeit() {
        return bezahlzeit;
    }

    // get the state of Tickets
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }



    public double rounded(double value) {
        return (double)Math.round(value * 100d) / 100d;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setVerlustGeb(double verlustGeb) {
        this.verlustGeb = verlustGeb;
    }

    public double getNachzahlung() {
        return nachzahlung;
    }

    public void setNachzahlung(double nachzahlung) {
        this.nachzahlung = nachzahlung;
    }

    public double getPrice() {
        return price;
    }

    public void setAustrittszeit(LocalDateTime austrittszeit) {
        this.austrittszeit = austrittszeit;
    }
    public void setEintrittszeit(LocalDateTime eintrittszeit) {this.eintrittszeit= eintrittszeit;}

    public void setBezahlzeit(LocalDateTime bezahlzeit) { this.bezahlzeit = bezahlzeit;}
    public static double getTotalSales() {
        return totalSales;
    }
 public static List<Ticket> getSortedTicketsByPriceDescending() {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream()
                .filter(ticket -> ticket.getBezahlzeit() != null)
                .sorted((ticket1, ticket2) -> Double.compare(
                        ticket2.getPrice() + ticket2.getVerlustGeb() + ticket2.getNachzahlung(),
                        ticket1.getPrice() + ticket1.getVerlustGeb() + ticket1.getNachzahlung()
                ))
                .collect(Collectors.toList());
    }

    public static List<Ticket> getTicketsByState() {
        List<Ticket> tickets = getAllTickets();

        return tickets.stream()
                .filter(ticket -> ticket.getState() instanceof com.example.parhausprj.TicketVerloren)
                .collect(Collectors.toList());
    }
    public static List<Ticket> getPaidTickets() {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream()
                .filter(ticket -> ticket.getBezahlzeit() != null)
                .collect(Collectors.toList());
    }
    public static List<Ticket> getunPaidTickets() {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream()
                .filter(ticket -> ticket.getBezahlzeit() == null)
                .collect(Collectors.toList());
    }
    public static List<Ticket> getOutTickets() {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream()
                .filter(ticket -> ticket.austrittszeit != null)
                .collect(Collectors.toList());
    }


}
