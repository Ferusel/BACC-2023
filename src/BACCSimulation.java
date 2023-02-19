import java.util.ArrayList;

class BACCSimulation extends Simulation {
    private final int MAX_ITEMS = 890;
    private final ArrayList<Event> initEvents;
    private final Company c;

    public BACCSimulation() {
        this.initEvents = new ArrayList<>();
        this.c = new Company();

        for (int i = 0; i < MAX_ITEMS; i++) {
            int arrivalTime = i * 5;
            Item l = new Item(arrivalTime, 1);
            this.initEvents.add(new EventArrival(arrivalTime, l, Company.getBuildingX()));
        }
    }


    @Override
    public ArrayList<Event> getInitialEvents() {
        return initEvents;
    }
}
