package pl.edu.agh.eventhub.producer;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import org.apache.commons.lang3.RandomUtils;

import java.util.stream.DoubleStream;

public class EventProducer {
    private final EventHubClient eventHubClient;

    public EventProducer(EventHubClient eventHubClient) {
        this.eventHubClient = eventHubClient;
    }

    public void produceFiniteDoubleStreamInRange(double lowerBound, double upperBound) {
        DoubleStream
                .generate(() -> RandomUtils.nextDouble(lowerBound, upperBound))
                .limit(100)
                .forEach(this::publishEvent);
    }

    private void publishEvent(Double aDouble) {
        try {
            final EventData eventData = new EventData(messageFromValue(aDouble));
            eventHubClient.send(eventData).get();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't send message", e);
        }
    }

    private byte[] messageFromValue(Double aDouble) {
        final String sensorId = String.format("sensor-%d", RandomUtils.nextInt(0, 2));
        final WaterSensorMeasurement sensorMeasurement = new WaterSensorMeasurement(sensorId, aDouble);
        return JsonSerializer.writeValueAsJsonByteArray(sensorMeasurement);
    }

}
