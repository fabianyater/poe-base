package main.types;

public enum StateType {
    ACTIVE("Active"), INACTIVE("Inactive"), SUSPENDED("Paused");

    private final String description;

    StateType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
