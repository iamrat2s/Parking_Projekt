package com.example.parhausprj;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class myLocalDate {


    private static long delta = 0;

    public static LocalDateTime myCurrentTime () {
        return LocalDateTime.now().plusMinutes(delta);
    }

    public synchronized static void timewarp (long addMinuten) {
        delta += addMinuten;
    }
    public static void setDelta(long delta) {
        myLocalDate.delta = delta;
    }


}
