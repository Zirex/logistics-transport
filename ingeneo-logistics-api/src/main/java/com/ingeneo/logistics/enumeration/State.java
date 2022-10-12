package com.ingeneo.logistics.enumeration;

public enum State {
    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE");
    private final String state;

    private State(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
