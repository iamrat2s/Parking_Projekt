package com.example.parhausprj;

public interface StatesInt {
    State bezahle(Ticket t);
    State verliere(Ticket t);
    State verlasse(Ticket t);
}
