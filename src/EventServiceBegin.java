/**
 * Only created when Item is created
 */
class EventServiceBegin extends Event {
    private final Item i;
    private final Station s;
    private final Building b;

    public EventServiceBegin(double time, Station s, Item i, Building b) {
        super(time, EventPriorityEnum.P_ServiceBegin);
        this.b = b;
        this.s = s;
        this.i = i;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": EVENT_SERVICE_BEGIN : %s service begin at %s", this.i, this.s);
    }


    @Override
    public Event[] simulate() {
        if (!this.s.isAvailable()) { 
            return new Event[]{
                new EventJoinStationQueue(this.getTime(), this.s, this.i, this.b)
            }; 
        }
        this.s.makeBusy(this.i);
        int currStep = this.i.getCurrStep();
        // Process item
        this.i.processToNextStep();
        return new Event[]{
                new EventServiceEnd(this.getTime() + Company.getProcessingTime(this.s, currStep), this.s, this.i, this.b)
        };
    }
}
