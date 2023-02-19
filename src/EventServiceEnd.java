import java.util.ArrayList;

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
        return super.toString() + String.format(": EVENT_SERVICE_END : %s service end at %s", this.i, this.s);
    }


    @Override
    public Event[] simulate() {
        this.s.makeAvailable();

        ArrayList<Event> res = new ArrayList<>();
        if (this.s.getQueueLength() > 0) {
            // Take the next in queue and process it
            res.add(new EventServiceBegin(this.getTime(), this.s, this.s.dequeueItem(), this.b));
        }

        if (this.i.isCompleted()) {
            res.add(new EventCompleteItem(this.getTime(), this.i));
        } else {
            res.add(new EventProcessItem(this.getTime(), this.i, this.b));
        }
       
        

        Event[] finalRes = new Event[res.size()];
        for (int i = 0; i < finalRes.length; i++) {
            finalRes[i] = res.get(i);
        }
        return finalRes;
    }
}
