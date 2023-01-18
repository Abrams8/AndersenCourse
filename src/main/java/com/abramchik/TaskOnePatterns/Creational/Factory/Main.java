package com.abramchik.TaskOnePatterns.Creational.Factory;

public class Main {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle vehicle1 = vehicleFactory.createVehicle(VehicleType.MOTOCYCLE);
        vehicle1.info();

        Vehicle vehicle2 = vehicleFactory.createVehicle(VehicleType.CAR);
        vehicle2.info();
    }
}
