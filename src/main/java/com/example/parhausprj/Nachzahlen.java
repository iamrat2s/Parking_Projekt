package com.example.parhausprj;

import java.time.Duration;
import java.time.LocalDateTime;

public class Nachzahlen  extends State {

    @Override
    public State verlasse(Ticket t) throws IllegalStateException {
        throw new IllegalStateException("You need to pay the additional fees first ");
    }

    @Override
    public State verliere(Ticket t) {
        t.setVerlustGeb(t.getVerlustGeb() + 30.0);
        return new TicketVerloren();
    }

    @Override
    public State bezahle(Ticket t) {
        LocalDateTime date = myLocalDate.myCurrentTime();
        double neuezeit = Duration.between( t.getBezahlzeit(),date).toHours() + ((double)((Duration.between(t.getBezahlzeit(),date)).toMinutes())%60.)/60.0;
        t.setPrice(t.getTicketPrice() * neuezeit);
        t.setBezahlzeit(date);

        return new TicketBezahlt() ;

    }


}
