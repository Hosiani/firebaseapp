package com.android.firebaseapp;

import android.app.Application;


public class MyApp extends Application {

    private Double testDouble = 36.5608189;
    private Double testDouble1 = 139.8757269;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public double getDouble() {
        return testDouble;
    }

    public void setDouble(Double id) {
        testDouble = id;
    }

    public double getDouble1() {
        return testDouble1;
    }

    public void setDouble1(Double kd) {
        testDouble1 = kd;
    }

}
