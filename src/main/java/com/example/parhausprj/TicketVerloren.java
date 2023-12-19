package com.example.parhausprj;

import java.time.Duration;

public class TicketVerloren extends State {
    @Override
    public State bezahle(Ticket t) {
        if (t.getPrice() == 0.0) {
            double dauer = t.ticketValidieren();
            t.setPrice(rounded((t.getTicketPrice()*dauer)+t.getVerlustGeb()));
        } else {
            double duration = Duration.between(t.bezahlzeit,myLocalDate.myCurrentTime()).toHours()
                    + (double)(Duration.between(t.bezahlzeit,myLocalDate.myCurrentTime()).toMinutes()%60)/60.0;
            if (duration <= 0.5) {
                t.setPrice(30.0);

            } else{
                t.setPrice(rounded((t.getTicketPrice()*duration)+t.getVerlustGeb()));

            }

        }
        t.setBezahlzeit(myLocalDate.myCurrentTime());
        return new TicketBezahlt();
    }

    @Override
    public State verliere(Ticket t) {
        return new TicketVerloren();
    }

    @Override
    public State verlasse(Ticket t) {
        throw new IllegalStateException("pay for your lost Ticket");
    }
}
