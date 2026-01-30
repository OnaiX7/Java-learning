package de.provadis.utils;

import java.text.DecimalFormat;

public class formatNumber {

    private static DecimalFormat df = new DecimalFormat("#,##0.00");

    public static String format(int number) {
        return df.format(number / 100.0);
    }

    // Überladene Methode für double-Werte
    public static String format(double number) {
        return df.format(number);
    }
}