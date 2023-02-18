import java.util.ArrayList;

/**
 * Occurs only if we know the transportation will definitely occur
 */
class EventTransport extends Event {
    private final Building itemsLocation;
    private final boolean isReturnJourney;

    public EventTransport(double time, Building itemsLocation, boolean isReturnJourney) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.itemsLocation = itemsLocation;
        this.isReturnJourney = isReturnJourney;
    }


    @Override
    public String toString() {
        return super.toString() + String.format(": Event transport called");
    }

    @Override
    public Event[] simulate() {
        return new Event[]{ new EventTruckBeginJourney(this.getTime(), this.itemsLocation, this.isReturnJourney) };


    }
}
