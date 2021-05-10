package com.icedborn.sportsmanager.ui.sports;

public class SportModel {
    /*Άθλημα με ενδεικτικά πεδία
	    1. Κωδικός Αθλήματος
	    2. Όνομα Αθλήματος
	    3. Είδος Αθλήματος (Ατομικό / Ομαδικό)
	    4. Φύλλο*/

    public int code;
    public String name;
    public String type;
    public String gender;

    public SportModel(int code, String name, String type, String gender) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.gender = gender;
    }
}
