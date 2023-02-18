import java.util.Map;
import java.util.PriorityQueue;

public class Company {
    static Station stationA = new Station(new int[]{ 1, 3 }, "StationA", 0);
    static Station stationB = new Station(new int[]{ 2, 6 }, "StationB", 1);
    static Station stationC = new Station(new int[]{ 2, 5 }, "StationC", 2);
    static Station stationD = new Station(new int[]{ 1, 4 }, "StationD", 3);
    static Station stationE = new Station(new int[]{ 1, 3, 5 }, "StationE", 4);
    static Station stationF = new Station(new int[]{ 4, 6 }, "StationF", 5);
    static TruckStation truckStationX = new TruckStation();
    static TruckStation truckStationY = new TruckStation();

    static Building buildingX = new Building(new Station[]{ stationA, stationB, stationC, truckStationX }, "BuildingX");
    static Building buildingY = new Building(new Station[]{ stationD, stationE, stationF, truckStationY }, "BuildingY");

    static Truck truck = new Truck(buildingX, buildingY);
    static int completeCount;

    // Map of { Step : Station[] }
    static final Map<Integer, Station[]> stepToStationMap = Map.ofEntries(
            Map.entry(1, new Station[]{ stationA, stationD, stationE }),
            Map.entry(2, new Station[]{ stationB, stationC }),
            Map.entry(3, new Station[]{ stationA, stationE }),
            Map.entry(4, new Station[]{ stationD, stationF }),
            Map.entry(5, new Station[]{ stationC, stationE }),
            Map.entry(6, new Station[]{ stationB, stationF })
    );

    // Map of { Station: { Step : Time} }
    static final Map<Station, Map<Integer, Integer>> stationToProcessTimeMap = Map.ofEntries(
            Map.entry(stationA, Map.ofEntries(
                    Map.entry(1, 5),
                    Map.entry(3, 10))
            ),
            Map.entry(stationB, Map.ofEntries(
                    Map.entry(2, 15),
                    Map.entry(6, 10)
            )),
            Map.entry(stationC, Map.ofEntries(
                    Map.entry(2, 15),
                    Map.entry(5, 10)
            )),
            Map.entry(stationD, Map.ofEntries(
                    Map.entry(1, 5),
                    Map.entry(4, 15)
            )),
            Map.entry(stationE, Map.ofEntries(
                    Map.entry(1, 5),
                    Map.entry(3, 5),
                    Map.entry(5, 15)
            )),
            Map.entry(stationF, Map.ofEntries(
                    Map.entry(4, 10),
                    Map.entry(6, 10)
            ))
    );

    public static Building getBuildingX() {
        return buildingX;
    }

    public static Station getNextStation(Item i, Building buildingContainingItem) {
        // Guard clause, just in case
        int step = i.getCurrStep();
        if (step >= 7) {
            return null;
        }

        // Sort next possible stations using PQ. Next possible stations include
        Station[] nextPossibleStations = stepToStationMap.get(step);
        PriorityQueue<Station> sortedStations = new PriorityQueue<>();
        for (Station x : nextPossibleStations) {
            sortedStations.add(x);
        }

        if (buildingContainingItem == buildingX) {
            sortedStations.add(truckStationX);
        } else {
            sortedStations.add(truckStationY);
        }

        Station nextStation = sortedStations.poll();
        if (buildingContainingItem.containsStation(nextStation)) {
            // No need to move Item to transport queue
            return nextStation;
        }

        // Next station is in the other building. We just return the TruckStation to await it for transport.
        if (buildingX.containsStation(nextStation)) {
            return truckStationX;
        }
        return truckStationY;
    }

    public static Building getBuildingAtStation(Station s) {
        if (buildingX.containsStation(s)) {
            return buildingX;
        }
        return buildingY;
    }

    public static int getProcessingTime(Station s, Item i) {
        return stationToProcessTimeMap.get(s).get(i.getCurrStep());
    }

    public static Truck getTruck() {
        return truck;
    }

    public static void completeOneProduct() {
        completeCount++;
    }

    @Override
    public String toString() {
        return "Company object, should not be called";
    }
}
