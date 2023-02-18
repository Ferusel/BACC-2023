import java.util.ArrayList;

class EventTransportDelay extends Event {
    private final Building b;
    private final int DELAY_TIME = 5;

    public EventTransportDelay(double time, Building itemsLocation) {
        super(time, EventPriorityEnum.P_TruckTransport);
        this.b = itemsLocation;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Delaying occurred, checking if Truck can transport now. ");
    }

    @Override
    public Event[] simulate() {
        if (Company.isBuildingX(this.b) && this.b.getTransportQueueLength() >= 5) {
            if (Company.getBuildingX() == Company.getTruck().getCurrentLocation()) {
                return new Event[]{
                        new EventTransport(this.getTime(), this.b, false)
                };
            } else {
                return new Event[]{
                        new EventTransportDelay(this.getTime() + DELAY_TIME, this.b)
                };
            }
        }
        return new Event[]{};
    }
}
