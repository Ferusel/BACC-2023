/**
 * Only created when Item is created
 */
class EventJoinTruckQueue extends Event {
    private final Item i;
    private final Building b;
    static boolean has_moved = false;

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
        if (!EventJoinTruckQueue.has_moved && b.getTransportQueueLength() == 5) {
            EventJoinTruckQueue.has_moved = true;
            return new Event[]{
                    new EventTransport(this.getTime())
            };
        }

        return new Event[]{};
    }
}
