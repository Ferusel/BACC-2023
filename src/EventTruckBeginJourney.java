/**
 * Only created when Item is created
 */
class EventTruckBeginJourney extends Event {
    private final Truck truck;
    private final int TRANSPORT_TIME = 25;

    public EventTruckBeginJourney(double time) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.truck = Company.getTruck();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Truck now leaving %s", this.truck.getCurrentLocation());
    }


    @Override
    public Event[] simulate() {
        return new Event[]{
                new EventTruckEndJourney(this.getTime() + TRANSPORT_TIME)
        };
    }
}
