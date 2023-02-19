import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Only created when Item is created
 */
class EventTruckEndJourney extends Event {
    private final Truck truck;
    private final ArrayList<Item> items;

    public EventTruckEndJourney(double time, ArrayList<Item> deliveredItems) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.truck = Company.getTruck();
        this.items = deliveredItems;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": EVENT_TRUCK_END_JOURNEY : Truck now reaching %s",
                this.truck.getDestination());
    }


    @Override
    public Event[] simulate() {
        //ArrayList<Item> deliveredItems = truck.getCurrentLocation().getTopFiveItems();
        Event[] res = new Event[items.size() + 1];

        for (int i = 0; i < res.length - 1; i++) {
            res[i] = new EventProcessItem(this.getTime(), items.get(i), truck.getDestination());
        }

        res[res.length - 1] = new EventTransport(this.getTime());
        truck.swapLocations();
        return res;
    }
}
