public class Bank {
  private Array<BankCounter> counters;
  private Queue<Customer> queue;

  public Bank(int numOfCounters, int maxCounterQueueLength, int maxEntraceQueueLength) {
    counters = new Array<BankCounter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      counters.set(i, new BankCounter(maxCounterQueueLength));
    }
    this.queue = new Queue<Customer>(maxEntraceQueueLength);
  }

  boolean queueIsFull() {
    return queue.isFull();
  }

  boolean hold(Customer c) {
    return queue.enq(c); 
  }
  
  Customer release() {
    return queue.deq(); 
  }

  public BankCounter getShortestQueue() {
    BankCounter c = counters.min();
    if (!c.isAvailable() && c.queueIsFull()) {
      return null;
    }
    return c;
  }
  
  public BankCounter getAvailableCounter() {
    for (int i = 0; i < counters.getLength(); i++) {
      BankCounter c = counters.get(i);
      if (c.isAvailable()) {
        return c;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.queue.toString();
  }
}
