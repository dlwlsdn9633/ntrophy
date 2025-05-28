package com.ntrophy.domain.enums;

import com.ntrophy.util.Function;

public enum PostType {
    STEAM(1, "steam"),
    KAKAO(2, "kakao"),
    BROADCASTS(3, "broadcasts"),
    MATCH_HISTORY(4, "history");
    private int code;
    private String label;
    PostType(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public int getCode() {
        return this.code;
    }
    public String getLabel() { return this.label; }

    public static PostType fromCode(int code) {
        for (PostType type : PostType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PostType code: " + code);
    }
    public static PostType fromLabel(String label) {
        for (PostType type : PostType.values()) {
            if (type.getLabel().equals(Function.isNull(label))) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PostType label: " + label);
    }
}
