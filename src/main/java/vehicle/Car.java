package vehicle;

import lombok.experimental.SuperBuilder;
import vehicle.enums.CarType;

@SuperBuilder
public class Car extends Vehicle {
    private CarType type;

}
