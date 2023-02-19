/**
 * Only created when Item is created
 */
class EventArrival extends Event {
    private final Item i;
    private final Building b;

    public EventArrival(double time, Item i, Building b) {
        super(time, EventPriorityEnum.P_Arrival);
        this.i = i;
        this.b = b;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": EVENT_ARRIVAL : %s arrived", i);
    }


    // DD: Invariant: the only time an Event's time is changed is when it's being serviced or if it's being transported.
    @Override
    public Event[] simulate() {
        Station s = Company.getNextStation(this.i);
        if (s == null) {
            // Should never happen
            System.out.println("=====> BUG FOUND IN EVENTARRIVAL: Item completed but still called");
            return new Event[]{};
        }

        if (s instanceof TruckStation) {
            // Join Truck Queue of current building
            Building b = Company.getBuildingAtStation(s);
            return new Event[]{
                    new EventJoinTruckQueue(this.getTime(), i, b)
            };
        } else if (s.isAvailable()) {
            // Station is available, immediately begin Event
            return new Event[]{
                    new EventServiceBegin(this.getTime(), s, i, b)
            };
        } else {
            // We queue the Item at the Station
            return new Event[]{
                    new EventJoinStationQueue(this.getTime(), s, i, b)
            };
        }
    }
}
