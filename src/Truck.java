import java.util.ArrayList;

public class Truck {
    private Building currentLocation;
    private Building destination;
    private ArrayList<Item> items;

    public Truck(Building buildingX, Building buildingY) {
        this.currentLocation = buildingX;
        this.destination = buildingY;
        this.items = new ArrayList<>();
    }

    public void loadTruck(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> unloadTruck() {
        ArrayList<Item> res = this.items;
        this.items = null;
        return res;
    }

    public Building getCurrentLocation() {
        return this.currentLocation;
    }

    public Building getDestination() {
        return destination;
    }

    public String getTruckDataInCsv() {
        String res = "";
        res += String.format("%s,", this.currentLocation.toString());
        for (int i = 0; i < this.items.size(); i++) {
            if (i == this.items.size() - 1) {
                res += String.format("%s", this.items.get(i));
            } else {
                res += String.format("%s,", this.items.get(i));
            }
        }
        return res;
    }

    public void setCurrentLocation(Building l) {
        this.currentLocation = l;
    }

    public void setDestination(Building d) {
        this.destination = d;
    }

    public void swapLocations() {
        Building tmp = this.currentLocation;
        this.currentLocation = this.destination;
        this.destination = tmp;
    }

}
