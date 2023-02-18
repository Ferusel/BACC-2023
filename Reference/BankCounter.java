class BankCounter implements Comparable<BankCounter> {
  private boolean available;
  private static int lastId = 0;
  private int id;

  public BankCounter() {
    this.makeAvailable();
    this.id = BankCounter.lastId;
    BankCounter.lastId ++;
  }

  Queue<Customer> queue;

  public BankCounter(int maxQueueLength) {
    this.queue = new Queue<Customer>(maxQueueLength);
    this.id = BankCounter.lastId;
    BankCounter.lastId ++;
    this.makeAvailable();
  }

  public boolean isAvailable() {
    return this.available;
  }

  public void makeBusy() {
    this.available = false;
  }

  public void makeAvailable() {
    this.available = true;
  }

  boolean add(Customer c) {
    return this.queue.enq(c);
  }

  public Customer nextCustomer() {
    return this.queue.deq();
  }

  public boolean queueIsFull() {
    return this.queue.isFull();
  }

  @Override
  public int compareTo(BankCounter c) {
    if (this.queue.length() == 0 && c.queue.length() == 0) {
      if (this.isAvailable() && c.isAvailable()) {
        return this.id - c.id;
      } else if (this.isAvailable()) {
        return -1;
      } else {
        return 1;
      }
    }
    return this.queue.length() - c.queue.length();
  }

  @Override
  public String toString() {
    return "S" + this.id + " " + this.queue;
    // return "S" + this.id;
  }
}
