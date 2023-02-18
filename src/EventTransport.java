/**
 * Only created when Item is created
 */
class EventTransport extends Event {
    private final int TRAVEL_TIME = 25;
    private final int DELAY_TIME = 25;
    private final Truck truck;
    private final Building src;
    private final Building dest;
    private final Building itemsLocation;
    private final Item[] items;

    public EventTransport(double time, Building itemsLocation) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.items = itemsLocation.getTopFiveItems();
        this.truck = Company.getTruck();
        this.src = this.truck.getCurrentLocation();
        this.dest = this.truck.getDestination();
        this.itemsLocation = itemsLocation;
    }


    @Override
    public String toString() {
        return super.toString() + String.format(": %s transporting items from %s to %s", this.src, this.dest);
    }

    @Override
    public Event[] simulate() {
        if (this.itemsLocation == this.truck.getCurrentLocation()) {
            // Can move
            truck.completeJourney();
            Event[] res = new Event[5];
            for (int i = 0; i < res.length; i++) {
                res[i] = new EventProcessItem(this.getTime() + TRAVEL_TIME, this.items[i], this.dest);
            }
            return res;
        } else {
            return new Event[]{
                    new EventTransport(this.getTime() + DELAY_TIME, this.itemsLocation)
            };
        }
    }
}
