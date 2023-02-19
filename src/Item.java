class Item implements Comparable<Item> {
    private static int lastId = 0;
    public final int id;
    private final double arrivalTime;
    private int currStep;
    private final int LAST_STEP = 7;
    private Building location;
    static final Item EMPTY_ITEM = new Item();

    public Item() {
        this.id = 0;
        this.arrivalTime = 0;
        this.currStep = 0;
        this.location = Company.getBuildingX();
    }

    public Item(double arrivalTime, int currStep) {
        this.id = ++lastId;
        this.arrivalTime = arrivalTime;
        this.currStep = currStep;
        this.location = Company.getBuildingX(); // DD: Initilaized to BuildingX
    }

    public void changeLocation(Building b) {
        this.location = b;
    }

    public Building getLocation() {
        return this.location;
    }

    public int getCurrStep() {
        return this.currStep;
    }

    public void processToNextStep() {
        this.currStep++;
    }

    public boolean isCompleted() {
        return this.currStep == LAST_STEP;
    }

    @Override
    public String toString() {
        if (this == Item.EMPTY_ITEM) {
            return "";
        }
        return "I" + id;
    }

    // DD: Order Item in a Queue based on how late in a Step it is
    @Override
    public int compareTo(Item i) {
        if (this.currStep > i.getCurrStep()) {
            return -1;
        } else if (this.currStep < i.getCurrStep()) {
            return 1;
        }
        if (this.id > i.id) {
            return 1;
        } else {
            return -1;
        }
    }
}
