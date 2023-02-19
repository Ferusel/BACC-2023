import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class Simulator {
    private final int MAX_TIME = 10800;

    private final PriorityQueue<Event> events;

    public Simulator(Simulation simulation) {
        this.events = new PriorityQueue<Event>();
        for (Event e : simulation.getInitialEvents()) {
            this.events.add(e);
        }
    }

    public void logToCsv(FileWriter fw, int time) throws IOException {
        // Logs the output to CSV
        fw.write(String.format("%s,%s\n", time, Company.getStationStatesAsString()));
    }

    public int run() {
        Event event = this.events.poll();
        try {
            FileWriter fw = new FileWriter("logged_results.csv");
            FileWriter completedItemsFw = new FileWriter("completed_items.csv");
            FileWriter logsFw = new FileWriter("logs.txt");
            fw.write(String.format(
                    "%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s," +
                            "%s\n",
                    "time",
                    "StationA_Item", "StationA_ItemStep",
                    "StationB_Item", "StationB_ItemStep",
                    "StationC_Item", "StationC_ItemStep",
                    "StationD_Item", "StationD_ItemStep",
                    "StationE_Item", "StationE_ItemStep",
                    "StationF_Item", "StationF_ItemStep",
                    "StationA_Queue",
                    "StationB_Queue",
                    "StationC_Queue",
                    "StationD_Queue",
                    "StationE_Queue",
                    "StationF_Queue",
                    "StationTruckX_Queue",
                    "StationTruckY_Queue",
                    "TruckDestination",
                    "TruckSlot1",
                    "TruckSlot2",
                    "TruckSlot3",
                    "TruckSlot4",
                    "TruckSlot5"
            ));
            completedItemsFw.write(String.format("%s, %s\n", "Time", "ItemID"));
            int t = 0;
            while (event != null && t <= MAX_TIME) {
                System.out.println(event);
                logsFw.write(event + "\n");
                if (event instanceof EventCompleteItem) {
                    completedItemsFw.write(String.format("%.0f, %s\n", event.getTime(), ((EventCompleteItem) event).getItem()));
                }
                int currT = Integer.valueOf(event.toString().split("[:]")[0]);
                if (currT != t) {
                    int diff = currT - t;
                    // New t
                    // If t skip is more than 5, e.g. 4820 -> 4830, we insert an additional row depending on the size
                    // of the skip
                    if (diff >= 10) {
                        int numOfRowsToDuplicate = (int) diff / 5;
                        for (int k = 0; k < numOfRowsToDuplicate; k++) {
                            this.logToCsv(fw, t);
                            t += 5;
                        }
                    } else {
                        this.logToCsv(fw, t);
                    }
                    t = currT;
                }
                Event[] newEvents = event.simulate();
                for (Event e : newEvents) {
                    this.events.add(e);
                }
                event = this.events.poll();
            }
            String finalMessage = String.format("Throughput: %s", Company.getThroughput());
            System.out.println(finalMessage);
            logsFw.write(finalMessage + "\n");

            fw.close();
            logsFw.close();
            completedItemsFw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Company.getThroughput();
    }
}
