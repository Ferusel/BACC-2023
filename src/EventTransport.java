import java.util.ArrayList;

class EventTransport extends Event {

    public EventTransport(double time) {
        super(time, EventPriorityEnum.P_TruckTransport);
    }


    @Override
    public String toString() {
        return super.toString() + String.format(": EVENT_TRANSPORT : Event transport called");
    }

    @Override
    public Event[] simulate() {
        return new Event[]{ new EventTruckBeginJourney(this.getTime()) };
    }
}
