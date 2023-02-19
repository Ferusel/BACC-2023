import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * This class implements a discrete event simulator.
 * The simulator maintains a priority queue of events,
 * and run through the events, simulate each one until
 * the queue is empty.
 *
 * @author Wei Tsang
 * @version CS2030S AY20/21 Semester 2
 */
public class Simulator {
    private final int MAX_LOOP = 50000;

    /**
     * The event queue.
     */
    private final PriorityQueue<Event> events;

    /**
     * The constructor for a simulator.  It takes in
     * a simulation as an argument, and calls the
     * getInitialEvents method of that simulation to
     * initialize the event queue.
     *
     * @param simulation The simulation to simulate.
     */
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

    /**
     * Run the simulation until no more events is in
     * the queue.  For each event in the queue (in
     * increasing order of time), print out its string
     * representation, then simulate it.  If the
     * simulation returns one or more events, add them
     * to the queue, and repeat.
     */
    public void run() {
        Event event = this.events.poll();
        int counter = 0;
        try {
            FileWriter fw = new FileWriter("logged_results.csv");
            FileWriter completedItemsFw = new FileWriter("completed_items.csv");
            FileWriter logsFw = new FileWriter("logs.txt");
            fw.write(String.format("%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s," +
                            "%s,%s\n",
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
                    "StationTruckY_Queue"
            ));
            completedItemsFw.write(String.format("%s, %s)", "Time", "ItemID"));
            int t = 0;
            while (event != null && counter < MAX_LOOP) {
                System.out.println(event);
                logsFw.write(event + "\n");
                if (event instanceof EventCompleteItem) {
                    completedItemsFw.write(String.format("%.0f, %s\n", event.getTime(), ((EventCompleteItem) event).getItem()));
                }
                int currT = Integer.valueOf(event.toString().split("[:]")[0]);
                if (currT != t) {
                    // New t
                    this.logToCsv(fw, t);
                    t = currT;
                }
                Event[] newEvents = event.simulate();
                for (Event e : newEvents) {
                    this.events.add(e);
                }
                event = this.events.poll();
                counter++;
            }

            fw.close();
            logsFw.close();
            completedItemsFw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
