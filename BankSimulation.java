import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY20/21 Semester 2
 */ 
class BankSimulation extends Simulation {
  private Bank bank;
  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int maxCounterQueueLength = sc.nextInt();
    int maxEntranceQueueLength = sc.nextInt();

    bank = new Bank(numOfCounters, maxCounterQueueLength, maxEntranceQueueLength);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int task = sc.nextInt();

      Customer c = new Customer(arrivalTime, serviceTime, getTaskFromInt(task));
      initEvents[id] = new ArrivalEvent(arrivalTime, c, bank);
      id += 1;
    }
  }


  public Task getTaskFromInt(int i) {
    if (i == 0) {
      return new Deposit();
    } else if (i == 1) { 
      return new Withdrawal();
    }else {
      return new OpenAccount();
    }
  }


  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
