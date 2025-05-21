package com.ntrophy.util;

public class Function {
    public static String isNull(String str) {
        return (str == null) ? "" : str;
    }
    public static double getRoundedDouble(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

}
