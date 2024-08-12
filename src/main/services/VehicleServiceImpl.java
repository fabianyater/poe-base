package main.services;

import main.models.Owner;
import main.models.Vehicle;
import main.types.StateType;
import main.types.VehicleType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static main.lib.utils.Utils.generateUniqueID;

public class VehicleServiceImpl implements VehicleService {
    private final List<Vehicle> filteredVehicles = new ArrayList<>();

    @Override
    public void loadData() {
        List<Vehicle> allVehicles = new ArrayList<>();

        Vehicle vehicle1 = new Vehicle(
                generateUniqueID(),
                "Toyota",
                "Corolla",
                "LE 1.8L CVT",
                "GDS324",
                "Vehículo confiable y económico, ideal para la ciudad.",
                new Owner(generateUniqueID(), "Juan Pérez", "Carrera 7 No. 15-90"),
                "Florencia",
                VehicleType.SEDAN,
                StateType.INACTIVE,
                "Negro",
                5,
                "./images/vehicles/toyota-corolla.png"
        );

        Vehicle vehicle2 = new Vehicle(
                generateUniqueID(),
                "Chevrolet",
                "Equinox",
                "Premier 1.5L Turbo",
                "FGH345",
                "SUV con gran capacidad de carga y comodidad.",
                new Owner(generateUniqueID(), "Ana Gómez", "Avenida 3 No. 22-45"),
                "San Vicente del Caguán",
                VehicleType.SUV,
                StateType.ACTIVE,
                "Azul Metálico",
                5,
                "./images/vehicles/chevrolet-equinox.png"
        );

        Vehicle vehicle4 = new Vehicle(
                generateUniqueID(),
                "Ford",
                "Ranger",
                "3.2L Diesel 4x4",
                "RTY345",
                "Pickup resistente y capaz para cualquier terreno.",
                new Owner(generateUniqueID(), "María Rodríguez", "Calle 2 No. 6-20"),
                "El Doncello",
                VehicleType.PICKUP,
                StateType.ACTIVE,
                "Rojo",
                5,
                "./images/vehicles/ford-ranger.png"
        );

        Vehicle vehicle5 = new Vehicle(
                generateUniqueID(),
                "Honda",
                "Odyssey",
                "EX-L 3.5L V6",
                "SDFS456",
                "Minivan espaciosa y cómoda para toda la familia.",
                new Owner(generateUniqueID(), "Luis Fernández", "Avenida 9 No. 16-25"),
                "Belén de los Andaquíes",
                VehicleType.VAN,
                StateType.ACTIVE,
                "Blanco",
                7,
                "./images/vehicles/honda-odissey.png"
        );

        Vehicle vehicle6 = new Vehicle(
                generateUniqueID(),
                "Mercedes-Benz",
                "C-Class",
                "C300 2.0L Turbo",
                "BUI140",
                "Coupe elegante y deportivo con acabados de lujo.",
                new Owner(generateUniqueID(), "Laura Sánchez", "Calle 5 No. 8-50"),
                "La Montañita",
                VehicleType.COUPE,
                StateType.INACTIVE,
                "Plata",
                4,
                "./images/vehicles/mercedes-benz.png"
        );

        Vehicle vehicle7 = new Vehicle(
                generateUniqueID(),
                "Ram",
                "1500",
                "Laramie Longhorn 5.7L V8",
                "FDF345",
                "Camioneta de gran capacidad con un motor potente.",
                new Owner(generateUniqueID(), "Miguel Ospina", "Carrera 10 No. 12-70"),
                "Cartagena del Chairá",
                VehicleType.TRUCK,
                StateType.SUSPENDED,
                "Gris",
                5,
                ""
        );


        allVehicles.add(vehicle1);
        allVehicles.add(vehicle2);
        allVehicles.add(vehicle4);
        allVehicles.add(vehicle5);
        allVehicles.add(vehicle6);
        allVehicles.add(vehicle7);
        filteredVehicles.addAll(allVehicles);
    }

    @Override
    public List<Vehicle> filterVehicles(String plate, String city, List<String> vehicleTypes, String stateType) {
        return filteredVehicles.stream()
                .filter(vehicle -> plate == null || vehicle.getPlate().equals(plate))
                .filter(vehicle -> city.equals("Select city") || vehicle.getCityPlate().equals(city))
                .filter(vehicle -> vehicleTypes.isEmpty() || vehicleTypes.contains(vehicle.getVehicleType().name()))
                .filter(vehicle -> stateType == null || vehicle.getStateType().getDescription().toLowerCase().equalsIgnoreCase(stateType))
                .toList();
    }

    @Override
    public int totalVehicles() {
        return filteredVehicles.size();
    }

    @Override
    public List<Vehicle> getFilteredVehicles() {
        Map<String, Integer> statePriority = Map.of(
                "Active", 1,
                "Inactive", 2,
                "Paused", 3
        );
        return filteredVehicles.stream()
                .sorted(Comparator.comparing(vehicle -> statePriority.get(vehicle.getStateType().getDescription())))
                .toList();
    }
}
