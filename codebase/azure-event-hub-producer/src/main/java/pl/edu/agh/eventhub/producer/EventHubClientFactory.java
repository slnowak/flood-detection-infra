package pl.edu.agh.eventhub.producer;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;

public class EventHubClientFactory {
    final static String namespaceName = "flood-detection-ns";
    final static String eventHubName = "flood-sensors-hub";
    final static String sasKeyName = "RootManageSharedAccessKey";
    final static String sasKey = "CHANGE_ME";

    public static EventHubClient create() {
        final ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);

        try {
            return EventHubClient.createFromConnectionString(connStr.toString()).get();
        } catch (Exception e) {
            throw new RuntimeException("Unrecognized exception", e);
        }
    }

}
