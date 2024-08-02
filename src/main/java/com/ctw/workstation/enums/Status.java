package com.ctw.workstation.enums;

public enum Status {
    ACTIVE, BOOKED, REPAIR, OUTDATED;

    public static Status fromString(String status) {
        return Status.valueOf(status.toUpperCase());
    }
}