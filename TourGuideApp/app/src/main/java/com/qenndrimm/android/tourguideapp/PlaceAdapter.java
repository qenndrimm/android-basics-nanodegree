package com.qenndrimm.android.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qenndrimm on 7/3/2016.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {
    private int mColorResourceId;

    public PlaceAdapter(Context context, ArrayList<Place> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Get the object located at this place on the list
        Place currentPlace = getItem(position);

        //Find the TextView in the list_item.xml layout with the ID txtTitle.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.txtTitle);
        miwokTextView.setText(currentPlace.getTitle());

        //Find the TextView in the list_item.xml layout with the ID txtDescription.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.txtDescription);
        defaultTextView.setText(currentPlace.getDescription());

        //Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imgFoto);
        imageView.setImageResource(currentPlace.getImageResourceId());

        //Set the theme color for the list item
        View container = listItemView.findViewById(R.id.container);

        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Set the background color of the text container View
        container.setBackgroundColor(color);

        return listItemView;
    }
}