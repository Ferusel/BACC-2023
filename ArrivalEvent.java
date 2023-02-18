class ArrivalEvent extends Event {
  private Customer c;
  private Bank s;

  public ArrivalEvent(double time, Customer c, Bank s) {
    super(time);
    this.c = c;
    this.s = s;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived %s", c, s);
  }

  @Override
  public Event[] simulate() {
    BankCounter counter = c.goToCounter(s);
    if (counter == null) {
      if (s.queueIsFull()) {
        return new Event[] { 
          new DepartureEvent(this.getTime(), c)
        };
      } else {
        return new Event[] { 
          new JoinBankQueueEvent(this.getTime(), c, s)
        };
      }
    } else if (counter.isAvailable()) {
      return new Event[] { 
        new ServiceBeginEvent(this.getTime(), c, counter, s)
      };
    } else {
      return new Event[] { 
        new JoinCounterQueueEvent(this.getTime(), c, counter)
      };
    }
  }
}
