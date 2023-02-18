import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Building {
    private final Queue<Station> stations; // Stations sorted by length of station. TODO: Is PQ unused??
    private final Queue<Item> transportItemQueue; // DD: Items waiting to be transported
    private final String buildingName;

    public Building(Station[] stations, String buildingName) {
        this.stations = new PriorityQueue<>();
        for (Station s : stations) {
            this.stations.add(s);
        }
        this.transportItemQueue = new PriorityQueue<>();
        this.buildingName = buildingName;
    }

    public boolean containsStation(Station s) {
        return this.stations.contains(s);
    }

    public void enqueueTransport(Item i) {
        this.transportItemQueue.add(i);
    }

    public Item dequeueTransport() {
        return this.transportItemQueue.poll();
    }

    public int getTransportQueueLength() {
        return this.transportItemQueue.size();
    }

    /**
     * Might not return a full list of Items, need to guard
     *
     * @return
     */
    public ArrayList<Item> getTopFiveItems() {
        ArrayList<Item> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Item x = this.dequeueTransport();
            if (x != null) {
                res.add(x);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return String.format("Building %s", this.buildingName);
    }

}
