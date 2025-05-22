package model;

import java.util.ArrayList;
import java.util.List;

public class TrafficModel {
    private List<Vehicle> northCars;
    private List<Vehicle> southCars;
    private List<Vehicle> eastCars;
    private List<Vehicle> westCars;

    public TrafficModel() {
        northCars = new ArrayList<>();
        southCars = new ArrayList<>();
        eastCars = new ArrayList<>();
        westCars = new ArrayList<>();

        initVehicles();
    }

    private void initVehicles() {
        // Kuzey (yukarı)
        northCars.add(new Vehicle(Direction.NORTH, 390, 600));
        northCars.add(new Vehicle(Direction.NORTH, 410, 620));
        northCars.add(new Vehicle(Direction.NORTH, 430, 610));

        // Güney (aşağı)
        southCars.add(new Vehicle(Direction.SOUTH, 410, -50));
        southCars.add(new Vehicle(Direction.SOUTH, 390, -70));
        southCars.add(new Vehicle(Direction.SOUTH, 430, -60));

        // Doğu (sağa)
        eastCars.add(new Vehicle(Direction.EAST, -50, 390));
        eastCars.add(new Vehicle(Direction.EAST, -70, 410));
        eastCars.add(new Vehicle(Direction.EAST, -60, 430));

        // Batı (sola)
        westCars.add(new Vehicle(Direction.WEST, 830, 410));
        westCars.add(new Vehicle(Direction.WEST, 850, 390));
        westCars.add(new Vehicle(Direction.WEST, 820, 430));
    }

    public List<Vehicle> getNorthCars() { return northCars; }
    public List<Vehicle> getSouthCars() { return southCars; }
    public List<Vehicle> getEastCars() { return eastCars; }
    public List<Vehicle> getWestCars() { return westCars; }
}

