public class TruckStation extends Station {

    // DD: Not exactly a station
    public TruckStation() {
        super(new int[]{ 1, 2, 3, 4, 5, 6 }, "Truck Station", 9999);
        // ID of 9999 to ensure it always loses when comparing between other Stations
    }
}
