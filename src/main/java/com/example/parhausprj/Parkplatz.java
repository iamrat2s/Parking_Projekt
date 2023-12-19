package com.example.parhausprj;

import java.util.ArrayList;
import java.util.List;

public class Parkplatz {

    private List<ParkingSpace> parkingSpaces;

    public Parkplatz(int numSpaces) {
        parkingSpaces = new ArrayList<>(numSpaces);
        for (int i = 1; i <= numSpaces; i++) {
            parkingSpaces.add(new ParkingSpace(i));
        }
    }
    public  ParkingSpace reserveParkingSpace(int spaceNumber,String autonummer) {
        for (ParkingSpace parkingSpace : parkingSpaces) {
            if (parkingSpace.getNumber() == spaceNumber && parkingSpace.isAvailable()) {
                if (Parkhauss.lots[spaceNumber-1] == null) {
                    parkingSpace.setAvailable(false);
                    parkingSpace.setAutonummer(autonummer);
                    Offnungzeitenservlet.Freeplaces--;
                    return parkingSpace;
                }

            }
        }
        return null;
    }
    public  ParkingSpace getSpace(int i) {
        return parkingSpaces.get(i);
    }

    public void releaseParkingSpace(ParkingSpace parkingSpace) {
        parkingSpace.setAvailable(true);
        parkingSpace.setAutonummer(null);
    }

    public class ParkingSpace {

        private String autonummer;
        private int number;
        private boolean available;

        public ParkingSpace(int number) {
            this.number = number;
            available = true;
        }

        public int getNumber() {
            return number;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
        public void setAutonummer(String autonummer) {
            this.autonummer = autonummer;
        }
        public String getAutonummer() {
            return autonummer;
        }


    }

}
