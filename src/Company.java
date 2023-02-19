import java.util.Map;

public class Company {
    static int STEP3 = 4;
    static int STEP5 = 2;

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
    static int completeCount = 0;

    static void resetCompany(int step3, int step5) {
        STEP3 = step3;
        STEP5 = step5;
        stationA = new Station(new int[]{ 1, 3 }, "StationA", 0);
        stationB = new Station(new int[]{ 2, 6 }, "StationB", 1);
        stationC = new Station(new int[]{ 2, 5 }, "StationC", 2);
        stationD = new Station(new int[]{ 1, 4 }, "StationD", 3);
        stationE = new Station(new int[]{ 1, 3, 5 }, "StationE", 4);
        stationF = new Station(new int[]{ 4, 6 }, "StationF", 5);
        truckStationX = new TruckStation();
        truckStationY = new TruckStation();

        buildingX = new Building(new Station[]{ stationA, stationB, stationC, truckStationX }, "BuildingX");
        buildingY = new Building(new Station[]{ stationD, stationE, stationF, truckStationY }, "BuildingY");

        truck = new Truck(buildingX, buildingY);
        completeCount = 0;

        stationToProcessTimeMap = Map.ofEntries(
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
    }

    // Map of { Step : Station[] }
    // static final Map<Integer, Station[]> stepToStationMap = Map.ofEntries(
    //         Map.entry(1, new Station[]{ stationA, stationD, stationE }),
    //         Map.entry(2, new Station[]{ stationB, stationC }),
    //         Map.entry(3, new Station[]{ stationA, stationE }),
    //         Map.entry(4, new Station[]{ stationD, stationF }),
    //         Map.entry(5, new Station[]{ stationC, stationE }),
    //         Map.entry(6, new Station[]{ stationB, stationF })
    // );

    // Map of { Station: { Step : Time} }
    static Map<Station, Map<Integer, Integer>> stationToProcessTimeMap = Map.ofEntries(
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

    public static String getStationStatesAsString() {
        // 12 columns to deal with
        String res = "";
        Station[] stations = new Station[]{ stationA, stationB, stationC, stationD, stationE, stationF };
        for (int i = 0; i < stations.length; i++) {
            Station s = stations[i];
            Item workedItem = s.peekItem();
            res += String.format("%s,%s,", workedItem.toString(), workedItem.getCurrStep());
        }
        // Populate queue representation

        for (int i = 0; i < stations.length; i++) {
            Station s = stations[i];
            String queueRep = s.getQueueRepresentation();
            res += String.format("%s,", queueRep);
        }
        res += String.format("%s,", buildingX.getTruckQueueRepresentation());
        res += String.format("%s,", buildingY.getTruckQueueRepresentation());

        return res;
    }

    public static Building getBuildingX() {
        return buildingX;
    }

    public static Building getBuildingY() {
        return buildingY;
    }

    public static Station getNextStation(Item i) {
        // Guard clause, just in case
        int step = i.getCurrStep();
        Building buildingContainingItem = i.getLocation();
        if (step >= 7) {
            return null;
        }

        switch (step) {
        case 1:
            return stationA;
        case 2:
            if (stationB.compareTo(stationC) < 0) {
                return stationB; // stationB is "smaller"
            }
            return stationC;
        case 3:
            if (buildingContainingItem == buildingX) {
                if (buildingContainingItem.getTransportQueueLength() >= STEP3) {
                    return stationA;
                }
                return truckStationX;
            } else {
                return stationE;
            }
        case 4:
            if (buildingContainingItem == buildingX) {
                return truckStationX;
            }
            // Item at Y, go to D or F based on shorter queue
//            if (stationD.getQueueLength() - 1 <= stationF.getQueueLength()) {
//                return stationD;
//            }

            if (stationD.getQueueLength() <= stationF.getQueueLength()) {
                return stationD;
            }

            return stationF;
        case 5:
            if (buildingContainingItem == buildingY) {
                if (buildingContainingItem.getTransportQueueLength() >= STEP5) {
                    return stationE;
                }
                return truckStationY;
            }
            return stationC;
        case 6:
            if (buildingContainingItem == buildingX) {
                return stationB;
            }
            return stationF;
        }

        return null; // Never reaches here

//        // Sort next possible stations using PQ. Next possible stations include
//        Station[] nextPossibleStations = stepToStationMap.get(step);
//        PriorityQueue<Station> sortedStations = new PriorityQueue<>();
//        for (Station x : nextPossibleStations) {
//            sortedStations.add(x);
//        }
//
//        if (buildingContainingItem == buildingX) {
//            sortedStations.add(truckStationX);
//        } else {
//            sortedStations.add(truckStationY);
//        }
//
//        Station nextStation = sortedStations.poll();
//        if (buildingContainingItem.containsStation(nextStation)) {
//            // No need to move Item to transport queue
//            return nextStation;
//        }
//
//        // Next station is in the other building. We just return the TruckStation to await it for transport.
//        if (buildingX.containsStation(nextStation)) {
//            return truckStationX;
//        }
//        return truckStationY;
    }

    public static int getThroughput() {
        return completeCount;
    }

    public static Building getBuildingAtStation(Station s) {
        if (buildingX.containsStation(s)) {
            return buildingX;
        }
        return buildingY;
    }

    public static int getProcessingTime(Station s, int currStep) {
        return stationToProcessTimeMap.get(s).get(currStep);
    }

    public static Truck getTruck() {
        return truck;
    }

    public static boolean isBuildingX(Building test) {
        return test == buildingX;
    }

    public static void completeOneProduct() {
        completeCount++;
    }

    @Override
    public String toString() {
        return "Company object, should not be called";
    }
}
