package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        // Find the TextView in the earthquake_activity.xml layout with the ID quake_mag
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);

        magTextView.setText(currentEarthquake.getQuake_mag());

        // Get quake city
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

        //TextView cityTextView = (TextView) listItemView.findViewById(R.id.city);

        //cityTextView.setText(currentEarthquake.getQuake_city());


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




}
