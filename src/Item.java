class Item implements Comparable<Item> {
    private static int lastId = 0;
    private final int id;
    private final double arrivalTime;
    private int currStep;
    private final int LAST_STEP = 6;

    public Item(double arrivalTime, int currStep) {
        this.id = ++lastId;
        this.arrivalTime = arrivalTime;
        this.currStep = currStep;
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
        return "I" + id;
    }

    // DD: Order Item in a Queue based on how late in a Step it is
    @Override
    public int compareTo(Item i) {
        if (this.currStep > i.getCurrStep()) {
            return 1;
        } else if (this.currStep < i.getCurrStep()) {
            return -1;
        }
        return 0;
    }
}
