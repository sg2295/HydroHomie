public enum GoalType {
    DAILY("Daily"),
    WEEKLY("Weekly");

    private String name;

    GoalType (String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return name;
    }
}
