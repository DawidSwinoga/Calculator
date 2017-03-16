package com.dawid.calculator.utils;

/**
 * Created by Dawid Świnoga on 16.03.2017.
 */

public class DoubleParser {
    public static Double parse(String str) {
        try {
            return Double.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
}
