package pl.edu.agh.eventhub.producer;

class WaterSensorMeasurement {
    private final String sensor;
    private final double value;

    WaterSensorMeasurement(String sensor, double value) {
        this.sensor = sensor;
        this.value = value;
    }

    public String getSensor() {
        return sensor;
    }

    public double getValue() {
        return value;
    }

}
