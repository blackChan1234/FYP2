package com.example.fyp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.EmptyStackException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test1Activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the user interface layout for this activity
        this.setContentView(R.layout.test1);
        tv=  findViewById(R.id.textView1);
        initiateFetchRestaurants();



    }
    private void initiateFetchRestaurants() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Replace these with your actual values or method calls to get them
            String coordinates = "{'lat': 40.7128, 'lon': -74.0060}";
            int maxDistance = 5000;

            // Background work: fetch the restaurants
            final String result = fetchRestaurants(coordinates, maxDistance);

            handler.post(() -> {
                // Update the UI Thread with the result
                tv.setText(result);
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
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EmptyStackException();
    }

}
