package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.example.Savesystem.Restaurant.NearbyRestaurant;


import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EmptyStackException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Location;


import com.example.fyp.adapters.NearbyRestaurantAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;

public class NearbyRestaurantsFragment extends Fragment implements LocationListener {
    Location location;
    Context thiscontext;
    LocationManager locMgr;
    TextView tv, tv2 , tv3,tv4,tv5;
    private RecyclerView nearFactsRecyclerView;
    NearbyRestaurantAdapter nearRestaurantAdapter;
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

        return inflater.inflate(R.layout.test1, container, false);


    }





private ArrayList<NearbyRestaurant> jsonStringToNearbyRestaurant(ArrayList<NearbyRestaurant> restaurantList,String jsonString) throws JSONException {
    JSONArray jsonArray = new JSONArray(jsonString);

    NearbyRestaurant r1;
    for (int i = 0; i < jsonArray.length(); i++) {
        Object item = jsonArray.get(i);
        if (item instanceof JSONObject) {
            JSONObject obj = (JSONObject) item;
            if(obj.isNull("name") || obj.length()<3)
                continue;
            String name = obj.optString("name", "No name provided");
            String address = obj.optString("address", "No address provided");
            JSONArray restaurantCoordinates = obj.getJSONObject("location").getJSONArray("coordinates");
            double distance = obj.getDouble("distance");
            r1= new NearbyRestaurant();
            r1.setLoc(address);
            r1.setName(name);
            r1.setDistance(distance);
            restaurantList.add(r1);

        }
    }
            return restaurantList;

}
    private void initiateFetchRestaurants() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Replace these with your actual values or method calls to get them

            String coordinates = "[114.1062036, 22.3425344]";
//            String coordinates = "["+location.getLongitude()+", "+location.getLatitude()+"]";

            int maxDistance = 5000;

            // Background work: fetch the restaurants
            final String result = fetchRestaurants(coordinates, maxDistance);

            handler.post(() -> {
                // Update the UI Thread with the result
                tv.setText(result);
                String jsonString =result;

                try {
                    ArrayList<NearbyRestaurant> restaurantList = new ArrayList<>();
                    jsonStringToNearbyRestaurant(restaurantList,jsonString);
                    if(!restaurantList.isEmpty()&&! (restaurantList.size() == 0)) {
                        nearRestaurantAdapter = new NearbyRestaurantAdapter(restaurantList, this);
                        nearFactsRecyclerView.setAdapter(nearRestaurantAdapter);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            });
        });
    }
    public String fetchRestaurants(String coordinates, int maxDistance) {
        // URL of the API endpoint
        String url = "http://10.0.2.2/phpcode/fypTest/api_postDetailRestaurant.php";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);

            // Set up the request headers
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            // Prepare the JSON payload
            String jsonPayload = String.format("{\"coordinates\": %s, \"maxDistance\": %d}", coordinates, maxDistance);

            // Attach the payload
            post.setEntity(new StringEntity(jsonPayload));
            // Execute the request
            //HttpRequest
            String result="";

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                result = EntityUtils.toString(response.getEntity());

            }


            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EmptyStackException();
    }





}
