package fixedspeedcamera;

import lombok.Builder;
import java.util.ArrayList;
import java.util.UUID;

@Builder
public class FixedSpeedCamera {
    private ConditionType condition;
    private final UUID uuid = UUID.randomUUID();
    private String location;
    private int speedLimit;
    private ArrayList<Measurement> measurements;

    public void repair() {
        if (condition != ConditionType.INTACT) {
            this.condition = ConditionType.INTACT;
        }
    }

}
