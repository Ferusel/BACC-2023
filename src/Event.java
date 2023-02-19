abstract class Event implements Comparable<Event> {
    private final double time;
    private final EventPriorityEnum eventPriority; // Represents the priority of an Event occurring given the time is the same
    // JoinStationQueue -> JoinTruckQueue -> ServiceEnd ->  TruckTransport -> EventArrival -> ServiceBegin

    public Event(double time, EventPriorityEnum eventPriority) {
        this.time = time;
        this.eventPriority = eventPriority;
    }

    public double getTime() {
        return this.time;
    }

    @Override
    public int compareTo(Event e) {
        if (this.time > e.time) {
            return 1;
        } else if (this.time == e.time) {
            if (this.eventPriority.getValue() > e.eventPriority.getValue()) {
                return -1;
            } else if (this.eventPriority.getValue() < e.eventPriority.getValue()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("%.0f", this.time);
    }

    public abstract Event[] simulate();
}
