class JoinCounterQueueEvent extends Event {
  private Customer c;
  private BankCounter cc;

  public JoinCounterQueueEvent(double time, Customer c, BankCounter cc) {
    super(time);
    this.c = c;
    this.cc = cc;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined counter queue (at %s)", c, cc);
  }

  @Override
  public Event[] simulate() {
    this.cc.add(c);
    return new Event[] { };
  }
}
