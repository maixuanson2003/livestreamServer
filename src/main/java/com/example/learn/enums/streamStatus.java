package com.example.learn.enums;

public enum streamStatus {
    PENDING("PENDING"),
    LIVE("LIVE"),
    ENDED("ENDED");

    private String label;

    streamStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
