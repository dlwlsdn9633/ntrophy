package com.ntrophy.dto.pubg.enums;

public enum GameMode {
    SOLO("solo"),
    SOLO_FPP("solo-fpp"),
    DUO("duo"),
    DUO_FPP("duo-fpp"),
    SQUAD("squad"),
    SQUAD_FPP("squad-fpp");
    private String label;
    GameMode(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
