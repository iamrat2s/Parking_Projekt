package com.example.parhausprj;

public class TicketGezogen extends State {

    @Override
    public State bezahle(Ticket t) {
        double dauer = t.ticketValidieren();
        t.setPrice(rounded(t.getTicketPrice()*dauer));
        return new TicketBezahlt();

    }

    @Override
    public State verliere(Ticket t) {
        t.setVerlustGeb(t.getVerlustGeb()+30.0);
        return new TicketVerloren();
    }

    @Override
    public State verlasse(Ticket t) {
        throw new IllegalStateException("You need to pay for your ticket first!");
    }
}
