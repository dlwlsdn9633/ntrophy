package com.ntrophy.dto.pubg.enums;

import com.ntrophy.util.Function;

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
    public static Platform fromLabel(String label) {
        for (Platform platform : Platform.values()) {
            if (platform.getLabel().equals(Function.isNull(label))) {
                return platform;
            }
        }
        throw new IllegalArgumentException("There is no label in this enum");
    }
}
