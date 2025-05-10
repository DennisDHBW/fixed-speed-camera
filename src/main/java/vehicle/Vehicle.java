package vehicle;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import person.Person;
import vehicle.enums.VehicleType;

@SuperBuilder
@Data
public abstract class Vehicle {
    protected int maxSpeed;
    protected Person owner;
    protected VehicleType vehicleType;

    @Builder.Default
    protected String licensePlate = null;
    @Builder.Default
    protected boolean isRegistrated = false;
}
