package person;

import fixedspeedcamera.FixedSpeedCamera;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@SuperBuilder
public class Technician extends Person {

    @Builder.Default
    ArrayList<FixedSpeedCamera> cameras = new ArrayList<>();

    public void repairCamera(FixedSpeedCamera camera) {
        camera.repair();
    }

    public void addCamera(FixedSpeedCamera camera) {
        cameras.add(camera);
    }

    @Override
    public String toString() {
        return String.format("Technician %s was born on %s", name, dateOfBirth.toString());
    }

}
