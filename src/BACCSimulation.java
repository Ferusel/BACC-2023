import java.util.ArrayList;

/**
 * This class implements a bank simulation.
 *
 * @author Wei Tsang
 * @version CS2030S AY20/21 Semester 2
 */
class BACCSimulation extends Simulation {
    private final int MAX_CYCLES = 2160;
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


        for (int i = 1; i < MAX_CYCLES + 1; i++) {
            if (i <= 1) {
                // First cycle, populate 6 items
                // Hardcode: 1 Item goes as EventArrival, 5 Item goes as EventJoinTruckQueue
                this.populateInitial();
            } else {
                int arrivalTime = i * 5;
                Item l = new Item(arrivalTime, 1);
                Station s = Company.getNextStation(l, Company.getBuildingX()); // first step
                this.initEvents.add(new EventArrival(arrivalTime, l, Company.getBuildingX()));
            }
        }
    }


    private void populateInitial() {
        for (int j = 0; j < 6; j++) {
            int arrivalTime = 0;
            Item l1 = new Item(arrivalTime, 1);
            Item l2 = new Item(arrivalTime, 1);
            Item l3 = new Item(arrivalTime, 1);
            Item l4 = new Item(arrivalTime, 1);
            Item l5 = new Item(arrivalTime, 1);
            Item l6 = new Item(arrivalTime, 1);
            Building buildingX = Company.getBuildingX();
            this.initEvents.add(new EventArrival(arrivalTime, l1, Company.getBuildingX()));
            this.initEvents.add(new EventJoinTruckQueue(arrivalTime, l2, buildingX));
            this.initEvents.add(new EventJoinTruckQueue(arrivalTime, l3, buildingX));
            this.initEvents.add(new EventJoinTruckQueue(arrivalTime, l4, buildingX));
            this.initEvents.add(new EventJoinTruckQueue(arrivalTime, l5, buildingX));
            this.initEvents.add(new EventJoinTruckQueue(arrivalTime, l6, buildingX));
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
