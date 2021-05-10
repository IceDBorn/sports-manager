package com.icedborn.sportsmanager.ui.teams;

public class TeamModel {
    /*Ομάδα με ενδεικτικά πεδία
	    1. Κωδικός Ομάδας
	    2. Όνομα
	    3. Όνομα γηπέδου
	    4. Πόλη (έδρα)
	    5. Χώρα
	    6. Κωδικός Αθλήματος
	    7. Έτος ίδρυσης*/

    public int code;
    public String name;
    public String stadium;
    public String city;
    public String country;
    public int sport;
    public String date;

    public TeamModel(int code, String name, String stadium, String city, String country, int sport, String date) {
        this.code = code;
        this.name = name;
        this.stadium = stadium;
        this.city = city;
        this.country = country;
        this.sport = sport;
        this.date = date;
    }
}
