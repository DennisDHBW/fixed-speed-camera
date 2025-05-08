package fixedspeedcamera;

import lombok.Builder;
import vehicle.Vehicle;

import java.time.LocalDateTime;

@Builder
public class Measurement {
    private LocalDateTime timestamp;
    private int speed;
    private Vehicle vehicle;

}
