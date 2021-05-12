package com.icedborn.sportsmanager.controllers;

import java.util.Calendar;

public class DateController {

    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month++;

        return makeDateString(day, month, year);
    }

    public static String makeDateString(int dayOfMonth, int month, int year) {
        // Γύρισε την ημερομηνία στη παρακάτω μορφή
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    private static String getMonthFormat(int month) {
        // Θέτει το μήνα σε κείμενο αντί για αριθμό
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "";
        }
    }
}
