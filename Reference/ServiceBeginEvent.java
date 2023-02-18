class ServiceBeginEvent extends Event {
  private Bank s;
  private Customer c;
  private BankCounter cc;

  public ServiceBeginEvent(double time, Customer c, BankCounter cc, Bank s) {
    super(time);
    this.s = s;
    this.c = c;
    this.cc = cc;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s begin (by %s)", c, c.getTask(), cc);
  }

  @Override
  public Event[] simulate() {
    this.cc.makeBusy();
    return new Event[] { 
      new ServiceDoneEvent(this.getTime() + this.c.getServiceTime(), this.c, this.cc, this.s)
    };
  }
}
