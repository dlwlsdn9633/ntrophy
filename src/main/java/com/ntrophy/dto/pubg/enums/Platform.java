package com.ntrophy.dto.pubg.enums;

public enum Platform {
    KAKAO("kakao"),
    STADIA("stadia"),
    STEAM("steam"),
    TOURNAMENT("tournament"),
    PSN("psn"),
    XBOX("xbox"),
    CONSOLE("console");
    private final String label;
    Platform(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
