class DepartureEvent extends Event {
  private Customer c;

  public DepartureEvent(double time, Customer c) {
    super(time);
    this.c = c;
  }

  public Event[] simulate() {
    return new Event[] { };
  }

  public String toString() {
    return super.toString() + String.format(": %s departed", c);
  }
}
