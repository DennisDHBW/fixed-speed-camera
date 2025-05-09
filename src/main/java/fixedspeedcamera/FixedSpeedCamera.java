package fixedspeedcamera;

import lombok.Builder;
import java.util.ArrayList;
import java.util.UUID;

@Builder
public class FixedSpeedCamera {
    private final UUID uuid = UUID.randomUUID();
    private String location;
    private int speedLimit;
    private ArrayList<Measurement> measurements;

    @Builder.Default
    private ConditionType condition = ConditionType.INTACT;

    public void repair() {
        if (condition != ConditionType.INTACT) {
            this.condition = ConditionType.INTACT;
        }
    }

}
