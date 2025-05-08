package fixedspeedcamera;

import stack.MyStack;

public class MeasurementStorage {
    MyStack<Measurement> measurements;

    public void saveMeasurement(Measurement measurement) {
        measurements.push(measurement);
    }

    public void deleteLastMeasurement() {
        measurements.pop();
    }

}
