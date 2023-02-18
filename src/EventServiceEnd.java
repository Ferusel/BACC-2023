/**
 * Only created when Item is created
 */
class EventServiceEnd extends Event {
    private final Item i;
    private final Station s;
    private final Building b;

    public EventServiceEnd(double time, Station s, Item i, Building b) {
        super(time, EventPriorityEnum.P_ServiceEnd);
        this.b = b;
        this.s = s;
        this.i = i;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": %s service end at %s", this.i, this.s);
    }


    @Override
    public Event[] simulate() {
        this.s.makeAvailable();

        if (this.i.isCompleted()) {
            return new Event[]{
                    new EventCompleteItem(this.getTime(), this.i)
            };
        }
        return new Event[]{
                new EventProcessItem(this.getTime(), this.i, this.b)
        };
    }
}
