package main.models;

import main.types.StateType;
import main.types.VehicleType;

public class Vehicle {

    private String code;
    private String brand;
    private String model;
    private String version;
    private String plate;
    private String description;
    private Owner owner;
    private String cityPlate;
    private VehicleType vehicleType;
    private StateType stateType;
    private String color;
    private int passengers;
    private String picture;

    public Vehicle() {
    }

    public Vehicle(String code, String brand, String model, String version, String plate, String description, Owner owner, String cityPlate, VehicleType vehicleType, StateType stateType, String color, int passengers, String picture) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.version = version;
        this.plate = plate;
        this.description = description;
        this.owner = owner;
        this.cityPlate = cityPlate;
        this.vehicleType = vehicleType;
        this.stateType = stateType;
        this.color = color;
        this.passengers = passengers;
        this.picture = picture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String brand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String model() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String version() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getCityPlate() {
        return cityPlate;
    }

    public void setCityPlate(String cityPlate) {
        this.cityPlate = cityPlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public StateType getStateType() {
        return stateType;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String picture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "code='" + code + '\'' +
                ", plate='" + plate + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", cityPlate='" + cityPlate + '\'' +
                ", vehicleType=" + vehicleType +
                ", stateType=" + stateType +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                ", picture='" + picture + '\'' +
                '}';
    }
}
