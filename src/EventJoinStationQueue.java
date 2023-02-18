/**
 * Only created when Item is created
 */
class EventJoinStationQueue extends Event {
    private final Station s;
    private final Item i;
    private final Building b;

    public EventJoinStationQueue(double time, Station s, Item i, Building b) {
        super(time, EventPriorityEnum.P_JoinStationQueue);
        this.i = i;
        this.b = b;
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": %s joined %s queue", i, s);
    }


    @Override
    public Event[] simulate() {
        this.s.enqueueItem(this.i);
        return new Event[]{};
    }
}
