package com.abramchik.taskOnePatterns.creational.factory;

public class VehicleFactory {
    public Vehicle createVehicle(VehicleType vehicleType){
        Vehicle vehicle = null;
        switch (vehicleType){
            case CAR:
                vehicle = new Car();
                break;
            case MOTOCYCLE:
                vehicle = new Motocycle();
                break;
        }
        return vehicle;
    }
}
