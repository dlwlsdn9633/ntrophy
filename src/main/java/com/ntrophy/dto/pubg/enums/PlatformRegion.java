package com.ntrophy.dto.pubg.enums;

public enum PlatformRegion {
    PC_AS("pc-as"),
    PC_EU("pc-eu"),
    PC_JP("pc-jp"),
    PC_KAKAO("pc-kakao"),
    PC_KRJP("pc-krjp"),
    PC_NA("pc-na"),
    PC_OC("pc-oc"),
    PC_RU("pc-ru"),
    PC_SA("pc-sa"),
    PC_SEA("pc-sea"),
    PC_TOURNAMENT("pc-tournament"),
    PSN_AS("psn-as"),
    PSN_EU("psn-eu"),
    PSN_NA("psn-na"),
    PSN_OC("psn-oc"),
    XBOX_AS("xbox-as"),
    XBOX_EU("xbox-eu"),
    XBOX_NA("xbox-na"),
    XBOX_OC("xbox-oc"),
    XBOX_SA("xbox-sa");
    private final String label;
    PlatformRegion(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
