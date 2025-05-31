package com.ntrophy.domain.enums;

import java.util.Arrays;

public enum Cmd {
    CREATE("create"),
    READ("read"),
    EDIT("edit"),
    DELETE("delete");
    private String label;
    Cmd(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    public static Cmd fromLabel(String label) {
        for (Cmd cmd : Cmd.values()) {
            if (cmd.getLabel().equals(label)) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("invalid cmd");
    }
}
