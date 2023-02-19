public enum EventPriorityEnum {
    P_CompleteItem(5), P_JoinStationQueue(7), P_JoinTruckQueue(6),
    P_ServiceEnd(9), P_TruckTransport(8),
    P_Arrival(11), P_ProcessItem(10), P_ServiceBegin(0);

    private final int value;

    EventPriorityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
