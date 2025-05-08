package vehicle;

import lombok.experimental.SuperBuilder;
import vehicle.enums.TruckType;

@SuperBuilder
public class Truck extends Vehicle {
    private TruckType type;
}
