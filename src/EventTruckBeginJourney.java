/**
 * Only created when Item is created
 */
class EventTruckBeginJourney extends Event {
    private final Building itemsLocation;
    private final boolean isReturnJourney;
    private final Truck truck;
    private final int TRANSPORT_TIME = 25;

    public EventTruckBeginJourney(double time, Building itemsLocation, boolean isReturnJourney) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.isReturnJourney = isReturnJourney;
        this.itemsLocation = itemsLocation;
        this.truck = Company.getTruck();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Truck now leaving %s", this.truck.getCurrentLocation());
    }


    @Override
    public Event[] simulate() {
        return new Event[]{
                new EventTruckEndJourney(this.getTime() + TRANSPORT_TIME, this.itemsLocation, this.isReturnJourney)
        };
    }
}
