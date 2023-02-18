class Customer {
  private static int lastId;
  private int id;
  private double arrivalTime;
  private double serviceTime;
  private Task task;

  public Customer(double arrivalTime, double serviceTime, Task task) {
    this.id = lastId++;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.task = task;
  }

  public BankCounter goToCounter(Bank s) {
    return s.getShortestQueue();
  }

  public Task getTask() {
    return this.task;
  }
  
  @Override
  public String toString() {
    return "C" + Integer.toString(id);
  }

  public double getServiceTime() {
    return this.serviceTime;
  }
}
