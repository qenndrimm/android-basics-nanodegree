package com.qenndrimm.android.tourguideapp;

/**
 * Created by qenndrimm on 7/3/2016.
 */
public class Place {
    /** Default title */
    private String mPlaceTitle;

    /** Default Description */
    private String mPlaceDescription;

    /** Image resource ID for the place */
    private int mImageResourceId;

    /**
     * Create a new Place object
     *
     * @param PlaceTitle is the name of the place
     * @param PlaceDescription are informations about that place
     * @param ImageResourceId the image resource id for that place
     */
    public Place(String PlaceTitle, String PlaceDescription, int ImageResourceId){
        mPlaceTitle = PlaceTitle;
        mPlaceDescription = PlaceDescription;
        mImageResourceId = ImageResourceId;
    }

    public String getTitle(){
        return mPlaceTitle;
    }

    public String getDescription(){
        return mPlaceDescription;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
}
