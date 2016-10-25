package com.qenndrimm.android.tourguideapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView restaurants = (TextView) findViewById(R.id.txtRestaurants);
        TextView publicPlaces = (TextView) findViewById(R.id.txtPublicPlaces);
        TextView events = (TextView) findViewById(R.id.txtEvents);
        TextView hotels = (TextView) findViewById(R.id.txtHotels);

        restaurants.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Restaurants Activity
                Intent restaurantInt = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(restaurantInt);
            }
        });

        publicPlaces.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Public Places Activity
                Intent pubPlacesInt = new Intent(MainActivity.this, PublicPlacesActivity.class);
                startActivity(pubPlacesInt);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Events Activity
                Intent eventInt = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(eventInt);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Hotels Activity
                Intent hotelsInt = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(hotelsInt);
            }
        });

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void addDrawerItems() {
        String[] navList = { "Restaurants", "Public Places", "Events", "Hotels"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navList);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent hotelsInt = new Intent(MainActivity.this, RestaurantsActivity.class);
                    startActivity(hotelsInt);
                }else if(position == 1){
                    Intent hotelsInt = new Intent(MainActivity.this, PublicPlacesActivity.class);
                    startActivity(hotelsInt);
                }else if(position == 2){
                    Intent hotelsInt = new Intent(MainActivity.this, EventsActivity.class);
                    startActivity(hotelsInt);
                }else{
                    Intent hotelsInt = new Intent(MainActivity.this, HotelsActivity.class);
                    startActivity(hotelsInt);
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}