import java.util.PriorityQueue;
import java.util.Queue;

class Station implements Comparable<Station> {
    private boolean available;
    private final String stationName;
    private final int[] serviceableSteps;
    private final int id;
    private final Queue<Item> queue;
    private Item currWorkedItem;

    public Station(int[] serviceableSteps, String stationName, int id) {
        this.makeAvailable();
        this.stationName = stationName;
        this.id = id;
        this.serviceableSteps = serviceableSteps; // The Steps that this Station can service
        this.queue = new PriorityQueue<>(); // Order by Item's latest in the Step
    }

    public boolean isAvailable() {
        return this.available;
    }

    public Item peekItem() {
        if (this.currWorkedItem == null) {
            return Item.EMPTY_ITEM;
        }
        return this.currWorkedItem;
    }

    public void makeBusy(Item item) {
        this.currWorkedItem = item;
        this.available = false;
    }

    public void makeAvailable() {
        this.currWorkedItem = null;
        this.available = true;
    }

    public Item dequeueItem() {
        return this.queue.poll();
    }

    public boolean enqueueItem(Item i) {
        return this.queue.add(i);
    }

    public Item getNextItem() {
        return this.queue.poll();
    }

    public int getQueueLength() {
        return this.queue.size();
    }

    public String getQueueRepresentation() {
//        String res = "[";
//        for (int i = 0; i < this.queue.size(); i++) {
//            res += this.queue.get(i);
//            if (i != this.queue.size() - 1) {
//                res += ",";
//            }
//        }
//        res += "]";
//        return res;
        return this.queue.toString().replace(",", " ");
    }

    /**
     * DD: Compare stations by length, then by ID
     *
     * @param c
     */
    @Override
    public int compareTo(Station c) {
        if (this.queue.size() == c.queue.size()) {
            // If queues of each station are the same length
            if (this.isAvailable() && c.isAvailable()) {
                // and both are available, just choose the station in lexicographic order
                // DD: Truck stations have ID of 9999.
                return this.id - c.id; // Compare based on id...?
            } else if (this.isAvailable()) {
                return -1;
            } else {
                return 1;
            }
        }
        return this.queue.size() - c.queue.size();
    }

    @Override
    public String toString() {
        return String.format("%s", this.stationName);
    }
}
