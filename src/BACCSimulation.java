import java.util.ArrayList;

/**
 * This class implements a bank simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY20/21 Semester 2
 */
class BACCSimulation extends Simulation {
    private final int MAX_ITEMS = 890;
    /**
     * The list of customer arrival events to populate
     * the simulation with.
     */
    private final ArrayList<Event> initEvents;
    private final Company c;

    /**
     * Constructor for a bank simulation.
     */
    public BACCSimulation() {
        this.initEvents = new ArrayList<>();
        this.c = new Company();

        for (int i = 0; i < MAX_ITEMS; i++) {
            int arrivalTime = i * 5;
            Item l = new Item(arrivalTime, 1);
            this.initEvents.add(new EventArrival(arrivalTime, l, Company.getBuildingX()));
        }
    }


    /**
     * Retrieve an array of events to populate the
     * simulator with.
     *
     * @return An array of events for the simulator.
     */
    @Override
    public ArrayList<Event> getInitialEvents() {
        return initEvents;
    }
}
