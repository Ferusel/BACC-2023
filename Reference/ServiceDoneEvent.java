class ServiceDoneEvent extends Event {
  private Bank s;
  private Customer c;
  private BankCounter cc;

  public ServiceDoneEvent(double time, Customer c, BankCounter cc, Bank s) {
    super(time);
    this.c = c;
    this.cc = cc;
    this.s = s;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s done (by %s)", c, c.getTask(), cc);
  }

  @Override
  public Event[] simulate() {
    this.cc.makeAvailable();
    Customer counterCustomer;
    Customer entranceCustomer;
    counterCustomer = this.cc.nextCustomer();
    entranceCustomer = this.s.release();
    if (counterCustomer == null && entranceCustomer == null) {
      return new Event[] { 
        new DepartureEvent(this.getTime(), this.c),
      };
    }

    // Assign entrance customer to this counter
    if (counterCustomer == null && entranceCustomer != null) {
      return new Event[] { 
        new DepartureEvent(this.getTime(), this.c),
        new ServiceBeginEvent(this.getTime(), entranceCustomer, this.cc, this.s),
      };
    }

    // Service the counter customer
    if (counterCustomer != null && entranceCustomer == null) {
      return new Event[] { 
        new DepartureEvent(this.getTime(), this.c),
        new ServiceBeginEvent(this.getTime(), counterCustomer, this.cc, this.s),
      };
    }

    // Service counter customer + join queue from entrance
    // When customer leaves, one slot is freed up in the queue,
    // so it's ok for the Customer to then join a Counter
    // queue
    return new Event[] { 
        new DepartureEvent(this.getTime(), this.c),
        new ServiceBeginEvent(this.getTime(), counterCustomer, this.cc, this.s),
        new JoinCounterQueueEvent(this.getTime(), entranceCustomer, this.cc)
      };
  }
}
