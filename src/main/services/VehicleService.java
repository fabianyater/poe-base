package main.services;

import main.models.Vehicle;

import java.util.List;

public interface VehicleService {
    void loadData();
    List<Vehicle> filterVehicles(String plate, String city, List<String> vehicleTypes, String stateType);
    int totalVehicles();
    List<Vehicle> getFilteredVehicles();
}
