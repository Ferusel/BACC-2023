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

    public void setCurrentLocation(Building l) {
        this.currentLocation = l;
    }

    public void setDestination(Building d) {
        this.destination = d;
    }

}
