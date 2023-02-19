/**
 * Only created when Item is created
 */
class EventJoinTruckQueue extends Event {
    private final Item i;
    private final Building b;
    private final int DELAY_TIME = 5;

    public EventJoinTruckQueue(double time, Item i, Building b) {
        super(time, EventPriorityEnum.P_JoinTruckQueue);
        this.i = i;
        this.b = b;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": EVENT_JOIN_TRUCK_QUEUE : %s joining truck queue at %s", i, b);
    }


    @Override
    public Event[] simulate() {
        this.b.enqueueTransport(this.i);

        // For BuildingY
        return new Event[]{};
    }
}
