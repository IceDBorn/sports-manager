package com.icedborn.sportsmanager.ui.matches;

public class MatchModel {
    /*Αγώνας με ενδεικτικά πεδία
        1. Κωδικός αγώνα
        2. Ομάδα πρώτη
        3. Ομάδα δεύτερη
        4. Άθλημα*/

    public int code;
    public int host;
    public int guest;
    public int sport;
    public String date;

    public MatchModel(int code, int host, int guest, int sport, String date) {
        this.code = code;
        this.host = host;
        this.guest = guest;
        this.sport = sport;
        this.date = date;
    }
}
