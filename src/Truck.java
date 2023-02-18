public class Truck {
    private Building currentLocation;
    private Building destination;

    public Truck(Building buildingX, Building buildingY) {
        this.currentLocation = buildingX;
        this.destination = buildingY;
    }

    public Building getCurrentLocation() {
        return this.currentLocation;
    }

    public Building getDestination() {
        return destination;
    }

    /**
     * Swaps currentLocation and destination
     */
    public void completeJourney() {
        Building tmp = this.currentLocation;
        this.currentLocation = this.destination;
        this.destination = tmp;
    }
}
