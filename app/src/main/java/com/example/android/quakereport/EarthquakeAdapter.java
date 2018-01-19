package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by curti on 1/18/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context,0, earthquakes);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        //Get object located at this position
        Earthquake currentEarthquake = getItem(position);


        // Magnitude Info
        // Find the TextView in the earthquake_activity.xml layout with the ID quake_mag
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);

        String magString = formatMag(currentEarthquake.getQuake_mag());
        magTextView.setText(magString);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
//
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getQuake_mag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        // Location Info
        String originalLocation = currentEarthquake.getQuake_city();
        int stringIndex = originalLocation.indexOf("of");
        String locationTop;
        String locationBottom;
        if(stringIndex != -1) {
            locationTop = originalLocation.substring(0, stringIndex + 2);
            locationBottom = originalLocation.substring((stringIndex + 2));

        } else {
            locationTop = getContext().getString(R.string.Near_the);
            locationBottom = originalLocation;
        }

        TextView cityTextViewTop = (TextView) listItemView.findViewById(R.id.cityTop);
        TextView cityTextViewBottom = (TextView) listItemView.findViewById(R.id.cityBottom);

        cityTextViewTop.setText(locationTop);
        cityTextViewBottom.setText(locationBottom.trim());




        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
       // SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String formattedDate = formatDate(dateObject);
        //Display the formatted date in 'date' TextView
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        //Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        //Display time of current Earthquake in 'time' TextView
        timeView.setText(formattedTime);




        // Date dateObject = new Date(timeInMilliseconds);
        //SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        //String dateToDisplay = dateFormatter.format(dateObject);


        // Return the whole list item layout (containing 3 textviews)
        return listItemView;


    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMag(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private int getMagnitudeColor(double doubleMag){
        int floorMag = (int) Math.floor(doubleMag);
        int returnColor;
        switch (floorMag){
            case 0:
            case 1:
                returnColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                returnColor =  R.color.magnitude2;
                break;
            case 3:
                returnColor =  R.color.magnitude3;
                break;
            case 4:
                returnColor =  R.color.magnitude4;
                break;
            case 5:
                returnColor =  R.color.magnitude5;
                break;
            case 6:
                returnColor =  R.color.magnitude6;
                break;
            case 7:
                returnColor =  R.color.magnitude7;
                break;
            case 8:
                returnColor =  R.color.magnitude8;
                break;
            case 9:
                returnColor =  R.color.magnitude9;
                break;
            default:
                returnColor =  R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), returnColor);// returnColor;
    }


}
