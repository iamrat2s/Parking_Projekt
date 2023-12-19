package com.example.parhausprj;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class TicketBezahlt extends State {
    @Override
    public State bezahle(Ticket t) {
        throw new IllegalStateException("You already Paid!");
    }

    @Override
    public State verliere(Ticket t) {
        t.setVerlustGeb(t.getVerlustGeb()+30.0);
        return new TicketVerloren();
    }

    @Override
    public State verlasse(Ticket t) {
        LocalDateTime date = myLocalDate.myCurrentTime();
        //double zwiscehnzeit = (date.getTime() - t.getBezahlzeit().getTime())/ (60.0 * 60.0 * 1000.0);
        Duration zwischenzeit = Duration.between(t.getBezahlzeit(),date);
        double duration = zwischenzeit.toHours()+ (double)zwischenzeit.toMinutes()/60.0 ;
        System.out.println(t.getBezahlzeit());
        System.out.println(date);
        System.out.println(duration);
        if (duration > 0.5) {
            return new Nachzahlen();
        }else {
            double price = Duration.between(t.getEintrittszeit(), t.getBezahlzeit()).toHours() + ((double)((Duration.between(t.getEintrittszeit(), t.getBezahlzeit()).toMinutes())%60.)/60.0);
            t.setPrice(rounded(price*t.getTicketPrice()));
            t.setAustrittszeit(myLocalDate.myCurrentTime());
            Offnungzeitenservlet.Freeplaces++;
            Parkhauss.lots[t.place] = null ; // empty parking place
            t.setOut(true);
            return new Ausgefahren();
        }
    }
}
