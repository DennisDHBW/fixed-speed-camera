package person;

import fixedspeedcamera.OwnerDatabase;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

@SuperBuilder
@Data
public class Person {
    protected String name;
    protected LocalDate dateOfBirth;

    @Builder.Default
    protected ArrayList<Vehicle> vehicles = new ArrayList<>();

    public String toString() {
        return String.format("Person %s was born on %s", name, dateOfBirth.toString());
    }

    public void buyVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setOwner(this);

    }

}
