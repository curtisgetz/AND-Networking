package com.example.android.quakereport;

/**
 * Created by curti on 1/18/2018.
 */

public class Earthquake {



    private double quake_mag;
    private String quake_city;
    //private String quake_date;
    private long mTimeInMilliseconds;



    public Earthquake(double eqMag, String eqCity, long timeInMilliseconds)
    {
        quake_mag = eqMag;
        quake_city = eqCity;
        //quake_date = eqDate;
        mTimeInMilliseconds = timeInMilliseconds;
    }



    public double getQuake_mag() {return quake_mag;}

    public String getQuake_city() {return quake_city;}

    //public String getQuake_date() {return quake_date;}

    public long getTimeInMilliseconds() {return mTimeInMilliseconds;}

}
