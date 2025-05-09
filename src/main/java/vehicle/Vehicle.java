package vehicle;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import person.Person;
import vehicle.enums.VehicleType;

@SuperBuilder
@Data
public abstract class Vehicle {
    protected int maxSpeed;
    protected String licensePlate;
    protected Person owner;
    protected VehicleType vehicleType;
}
