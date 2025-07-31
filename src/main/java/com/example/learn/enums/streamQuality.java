package com.example.learn.enums;

public enum streamQuality {
    _720P_2628KBS("_720p2628kbs", 2628000, "1280x720"),
    _480P_1128KBS("_480p1128kbs", 1128000, "854x480"),
    _360P_878KBS("_360p878kbs", 878000, "640x360"),
    _240P_528KBS("_240p528kbs", 528000, "426x240"),
    _240P_264KBS("_240p264kbs", 264000, "426x240");

    private final String label;
    private final int bandwidth;
    private final String resolution;

    streamQuality(String label, int bandwidth, String resolution) {
        this.label = label;
        this.bandwidth = bandwidth;
        this.resolution = resolution;
    }

    public String getLabel() {
        return label;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return String.format("%s [Bandwidth=%d, Resolution=%s]", label, bandwidth, resolution);
    }
}
