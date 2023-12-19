package com.example.parhausprj;

public abstract class State implements StatesInt{

    public double rounded(double value) {
        return (double)Math.round(value * 100d) / 100d;
    }
}
