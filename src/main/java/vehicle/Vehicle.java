package vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import person.Person;
import vehicle.enums.VehicleType;

@SuperBuilder
@Data
@AllArgsConstructor
public abstract class Vehicle {
    protected int maxSpeed;
    protected String licensePlate;
    protected Person owner;
    protected VehicleType vehicleType;
}
