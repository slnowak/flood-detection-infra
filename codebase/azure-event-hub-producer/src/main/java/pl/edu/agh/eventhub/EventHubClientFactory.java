package pl.edu.agh.eventhub;

import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;

class EventHubClientFactory {
    final static String namespaceName = "flood";
    final static String eventHubName = "flood-sensors-hub";
    final static String sasKeyName = "RootManageSharedAccessKey";
    final static String sasKey = "CHANGE_ME";

    static EventHubClient create() {
        final ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);

        try {
            return EventHubClient.createFromConnectionString(connStr.toString()).get();
        } catch (Exception e) {
            throw new RuntimeException("Unrecognized exception", e);
        }
    }

}
