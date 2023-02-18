class JoinBankQueueEvent extends Event {
  private Customer c;
  private Bank s;

  public JoinBankQueueEvent(double time, Customer c, Bank s) {
    super(time);
    this.c = c;
    this.s = s;
  }

  @Override
  public Event[] simulate() {
    this.s.hold(c);
    return new Event[] { };
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined shop queue %s", c, s);
  }
}
