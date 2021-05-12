package com.icedborn.sportsmanager.ui.athletes;

public class AthleteModel {
    /*Αθλητής με ενδεικτικά πεδία
        1. Κωδικός Αθλητή
        2. Όνομα
        3. Επώνυμο
        4. Πόλη (έδρα)
        5. Χώρα
        6. Κωδικός Αθλήματος
        7. Έτος γέννησης.*/

    public int id;
    public String name;
    public String surname;
    public String city;
    public String country;
    public int sport;
    public String date;

    public AthleteModel(int id, String name, String surname, String city, String country, int sport, String date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.sport = sport;
        this.date = date;
    }
}
