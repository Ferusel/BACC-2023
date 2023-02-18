import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Only created when Item is created
 */
class EventTruckEndJourney extends Event {
    private final Building itemsLocation;
    private final boolean isReturnJourney;
    private final Truck truck;
    private final ArrayList<Item> items;

    public EventTruckEndJourney(double time, Building itemsLocation, boolean isReturnJourney) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.isReturnJourney = isReturnJourney;
        this.itemsLocation = itemsLocation;
        this.truck = Company.getTruck();
        this.items = itemsLocation.getTopFiveItems();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Truck now reaching %s", this.truck.getDestination());
    }


    @Override
    public Event[] simulate() {
        // Come from building X
        // Guaranteed to have 5 items with the Truck
        if (!isReturnJourney) {
            truck.setCurrentLocation(Company.getBuildingY());
            truck.setDestination(Company.getBuildingX());
            Event[] res = new Event[6];

            for (int i = 0; i < 5; i++) {
                res[i] = new EventProcessItem(this.getTime(), this.items.get(i), Company.getBuildingX());
            }

            // Make the Event for the return journey
            res[5] = new EventTransport(this.getTime(), Company.getBuildingX(), true);
            return res;
        }

        // Occurs at building Y
        // This is the return journey, not necessarily 5 Items

        Event[] res = new Event[this.items.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = new EventProcessItem(this.getTime(), this.items.get(i), Company.getBuildingX());
        }
        return res;
    }
}
