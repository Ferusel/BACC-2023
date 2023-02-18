/**
 * Only created when Item is created
 */
class EventJoinTruckQueue extends Event {
  private final Item i;
  private final Building b;

  public EventJoinTruckQueue(double time, Item i, Building b) {
    super(time, EventPriorityEnum.P_JoinTruckQueue);
    this.i = i;
    this.b = b;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joining truck queue at %s", i, b);
  }


  @Override
  public Event[] simulate() {
    this.b.enqueueTransport(this.i);
    if (this.b.getTransportQueueLength() == 5) {
      return new Event[]{
              new EventTransport(this.getTime(), this.b)
      };
    }
    return new Event[]{};
  }
}
