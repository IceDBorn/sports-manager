package com.icedborn.sportsmanager;

import android.content.Context;

public class ContextSingletton {
    private static ContextSingletton instance;
    private ContextSingletton(Context context){
        if(instance!=null){

        }
    }
}
