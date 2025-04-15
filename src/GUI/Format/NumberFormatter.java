/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Format;

import java.text.DecimalFormat;

/**
 *
 * @author DELL
 */
public class NumberFormatter {
    public static String format(int number) {
        return new DecimalFormat("#,###").format(number).replace(',', '.');
    }

    public static String format(long number) {
        return new DecimalFormat("#,###").format(number).replace(',', '.');
    }

    public static String format(double number) {
        return new DecimalFormat("#,###.##").format(number).replace(',', '.');
    }
    public static String formatReverse(String str) {
    try {
        String raw = str.replace(".", "").trim();
        return raw;
    } catch (NumberFormatException e) {
        return "";
    }
}
}
