package com.ntrophy.domain.enums;

public enum PostType {
    STEAM(1),
    KAKAO(2),
    BROADCASTS(3),
    MATCH_HISTORY(4);
    private int code;
    PostType(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public static PostType fromCode(int code) {
        for (PostType type : PostType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PostType code: " + code);
    }
}
