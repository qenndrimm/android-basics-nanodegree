package com.qenndrimm.android.tourguideapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);

        // Create a list of Hotels
        final ArrayList<Place> words = new ArrayList<Place>();
        words.add(new Place("Swiss Dimond", "Pristina. Best Price Guarantee! No reservation costs. Great rates.", R.drawable.swiss_diamond));
        words.add(new Place("Grand Hotel", "Grand Hotel is one of the oldest hotels in Kosovo, standardized and licensed, with a professional staff and large space.", R.drawable.grand_hotel));
        words.add(new Place("Hotel Sirius", "Hotel Sirius is located in the center of Prishtina. It is one of the best hotels in Kosovo, which offers commodity and safety for all the guests....", R.drawable.sirius_hotel));
        words.add(new Place("Emerald Hotel", "The Emerald Hotel is a 5 star Priština hotel that is full of luxury and deluxe amenities to please even the most discerning guests.", R.drawable.emerald_hotel));
        words.add(new Place("Hotel Garden", "Pristina. Best Price Guarantee! No reservation costs. Great rates.", R.drawable.garden_hotel));
        words.add(new Place("Hotel Nartel", "Hotel Sirius is located in the center of Prishtina. It is one of the best hotels in Kosovo.", R.drawable.nartel_hotel));
        words.add(new Place("Hotel Victory", "Hotel Victory is in peaceful surroundings, just over half a mile from the center of Prishtine.", R.drawable.victory_hotel));

        // Creating list items using custom adapter
        PlaceAdapter adapter = new PlaceAdapter(this, words, R.color.category_hotels);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
