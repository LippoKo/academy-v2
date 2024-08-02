package com.ctw.workstation.enums;

public enum DefaultLocation {
    LISBON, PORTO, BRAGA;

    public static DefaultLocation fromString(String location) {
        return DefaultLocation.valueOf(location.toUpperCase());
    }
}
