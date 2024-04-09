package com.example.fyp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.Savesystem.Restaurant.NearbyRestaurant;
import com.example.Savesystem.Restaurant.Restaurant;

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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
public class NearbyRestaurantsFragment extends Fragment {

    TextView tv;
    String locCoordinate;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        // Set the user interface layout for this activity
//        this.setContentView(R.layout.test1);
//        tv=  findViewById(R.id.textView1);
//        initiateFetchRestaurants();
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

//        fetchLastLocation();
        return inflater.inflate(R.layout.test1, container, false);


    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv=  view.findViewById(R.id.textView1);
        initiateFetchRestaurants();
    }

private ArrayList<NearbyRestaurant> jsonStringToNearbyRestaurant(ArrayList<NearbyRestaurant> restaurantList,String jsonString) throws JSONException {
    JSONArray jsonArray = new JSONArray(jsonString);

    NearbyRestaurant r1;
    for (int i = 0; i < jsonArray.length(); i++) {
        Object item = jsonArray.get(i);
        if (item instanceof JSONObject) {
            JSONObject obj = (JSONObject) item;
            String name = obj.optString("name", "No name provided");
            String address = obj.optString("address", "No address provided");
            JSONArray restaurantCoordinates = obj.getJSONObject("location").getJSONArray("coordinates");
            double longitude = restaurantCoordinates.getDouble(0);
            double latitude = restaurantCoordinates.getDouble(1);
            double distance = obj.getDouble("distance");
            r1= new NearbyRestaurant();
            r1.setLoc(address);
            r1.setName(name);
            r1.setDistance(distance);
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
            int maxDistance = 5000;

            // Background work: fetch the restaurants
            final String result = fetchRestaurants(coordinates, maxDistance);

            handler.post(() -> {
                // Update the UI Thread with the result
                tv.setText(result);
                String jsonString =result;

                try {
//                    JSONArray jsonArray = new JSONArray(jsonString);
                    ArrayList<NearbyRestaurant> restaurantList = new ArrayList<>();
                    jsonStringToNearbyRestaurant(restaurantList,jsonString);


                    tv.setText(result);
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
            //public CloseableHttpResponse execute(ClassicHttpRequest  request)
//            CloseableHttpResponse response = client.execute(post);
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                result = EntityUtils.toString(response.getEntity());
//                System.out.println(EntityUtils.toString(response.getEntity()));
            }

//            System.out.println(result);
//            result+="\n"+jsonPayload;
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EmptyStackException();
    }




//    private void fetchLastLocation() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // 检查权限，如果没有权限，请求权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//            return;
//        }
//        Task<Location> task = fusedLocationClient.getLastLocation();
//        task.addOnSuccessListener((Executor) this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                // 在这里使用location对象
//                if (location != null) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//
//                    // 使用获取到的最后一个位置信息
//                }
//            }
//        });
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // 权限被授予，继续获取位置
//            fetchLastLocation();
//        }
//    }
}
