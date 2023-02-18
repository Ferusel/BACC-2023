/**
 * Only created when Item is created
 */
class EventCompleteItem extends Event {
    private final Item i;

    public EventCompleteItem(double time, Item i) {
        super(time, EventPriorityEnum.P_CompleteItem);
        this.i = i;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": One Item completed (%s)", this.i);
    }


    @Override
    public Event[] simulate() {
        Company.completeOneProduct();
        return new Event[]{};
    }
}