package com.stl.skipthelibrary.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.stl.skipthelibrary.DatabaseAndAPI.DatabaseHelper;
import com.stl.skipthelibrary.DatabaseAndAPI.GoodReadsAPIHelper;
import com.stl.skipthelibrary.Entities.Rating;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to get the books rating, it works with the GoodReadsAPIHelper
 */
public class BookRatingReceiver extends AsyncTask<String, Void, String> {
    private final String TAG = BookRatingReceiver.class.getSimpleName();
    private Rating rating;
    private Context context;
    private String uuid;

    /**
     * The constructor
     * @param rating: the books rating
     */
    public BookRatingReceiver(Context context, Rating rating, String uuid) {
        setRating(rating);
        setContext(context);
        setUuid(uuid);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Start the task that will be done in the background
     * @param strings: an unspecified amount of parameters, in this case it is the ISBN
     * @return the book info in JSON
     */
    @Override
    protected String doInBackground(String... strings) {
        return GoodReadsAPIHelper.getBookInfo(context,strings[0]);
    }

    /**
     * Converts the results of the API call in JSON to separate strings and binds them to UI
     * @param s: The results in JSON of the API call
     */
    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, s);
        super.onPostExecute(s);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
            JSONObject jsonObject = new JSONObject(s);
            Log.d(TAG, jsonObject.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("books");
            Log.d(TAG, jsonArray.toString());
            double averageRating = jsonArray.getJSONObject(0).getDouble("average_rating");
            int ratingsCount = jsonArray.getJSONObject(0).getInt("ratings_count");
            getRating().setAverageRating(averageRating);
            getRating().setCount(ratingsCount);
            Log.d(TAG, "onPostExecute: "+rating);
            Log.d(TAG, "onPostExecute: "+getUuid()+" "+getRating());

            databaseHelper.updateRating(getUuid(),getRating());


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
