import java.util.ArrayList;

/**
 * Occurs only if we know the transportation will definitely occur
 */
class EventTransport extends Event {

    public EventTransport(double time) {
        super(time, EventPriorityEnum.P_TruckTransport);
    }


    @Override
    public String toString() {
        return super.toString() + String.format(": Event transport called");
    }

    @Override
    public Event[] simulate() {
        return new Event[]{ new EventTruckBeginJourney(this.getTime()) };
    }
}
