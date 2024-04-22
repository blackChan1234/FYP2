package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Savesystem.Restaurant.Restaurant;
import com.example.fyp.adapters.RestaurantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment implements LocationListener {
    Location location;
    Context thiscontext;
    LocationManager locMgr;
    TextView tv, tv2 , tv3,tv4,tv5;
    private RecyclerView nearFactsRecyclerView;
    RestaurantAdapter RestaurantAdapter;
    String postResult;
    private boolean readyToLoad;

    @SuppressLint("MissingPermission")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //test tv
        tv=  view.findViewById(R.id.textView1);
        tv2=  view.findViewById(R.id.textView2);
        tv3=  view.findViewById(R.id.textView3);
        tv4=  view.findViewById(R.id.textView4);
        tv5=  view.findViewById(R.id.textView5);

        tv.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        tv5.setVisibility(View.GONE);

        nearFactsRecyclerView = view.findViewById(R.id.nearFactsRecyclerView);
        thiscontext = view.getContext();

        locMgr=(LocationManager) thiscontext.getSystemService(Context.LOCATION_SERVICE);

        location=locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        initiateFetchRestaurants();
    }


    @SuppressLint("MissingPermission")
    public void onResume(){
        super.onResume();
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000,
                10,
                this);
    }

    public void onPause(){
        super.onPause();
        locMgr.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
//        showLocation(location);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.nearby_restaurants_actity, container, false);


    }





    private ArrayList<Restaurant> jsonStringToRestaurant(ArrayList<Restaurant> restaurantList, String jsonString) throws JSONException {


        JSONArray jsonArray = new JSONArray(jsonString);

        Restaurant r1;
        for (int i = 0; i < jsonArray.length(); i++) {
            Object item = jsonArray.get(i);
            if (item instanceof JSONObject) {
                JSONObject obj = (JSONObject) item;
                if(obj.isNull("name") || obj.length()<3)
                    continue;
                String name = obj.optString("name", "No name provided");
                String address = obj.optString("address", "No address provided");
                r1= new Restaurant();
                r1.setLoc(address);
                r1.setName(name);
                restaurantList.add(r1);

            }
        }
            return restaurantList;
    }

    private void initiateFetchRestaurants() {

        try {
            ArrayList<Restaurant> restaurantList = new ArrayList<>();

            jsonStringToRestaurant(restaurantList, postResult);

            if(!restaurantList.isEmpty()&&! (restaurantList.size() == 0)) {
                RestaurantAdapter = new RestaurantAdapter(restaurantList);
                nearFactsRecyclerView.setAdapter(RestaurantAdapter);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void setPostResult(String postResult){
//        this.postResult = postResult;
        this.postResult = postResult;
        this.readyToLoad = true;

    }





}
