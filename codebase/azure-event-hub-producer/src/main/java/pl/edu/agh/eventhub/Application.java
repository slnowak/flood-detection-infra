package pl.edu.agh.eventhub;

import com.microsoft.azure.eventhubs.EventHubClient;
import pl.edu.agh.eventhub.producer.EventHubClientFactory;
import pl.edu.agh.eventhub.producer.EventProducer;

public class Application {

    public static void main(String[] args) throws Exception {
        final EventHubClient eventHubClient = EventHubClientFactory.create();
        final EventProducer eventProducer = new EventProducer(eventHubClient);

        System.out.println("Producing finite double stream of water measurements..");
        eventProducer.produceFiniteDoubleStreamInRange(3.0d, 7.0d);
        System.out.println("Job finished");

        eventHubClient.onClose().get();
    }

}
