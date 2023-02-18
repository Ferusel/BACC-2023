public enum EventPriorityEnum {
    P_CompleteItem(7), P_JoinStationQueue(6), P_JoinTruckQueue(5),
    P_ServiceEnd(4), P_TruckTransport(8),
    P_Arrival(2), P_ProcessItem(1), P_ServiceBegin(0);

    private final int value;

    EventPriorityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
