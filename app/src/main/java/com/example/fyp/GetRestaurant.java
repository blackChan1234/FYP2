package com.example.fyp;


//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;

import androidx.fragment.app.Fragment;

import org.apache.hc.client5.http.classic.methods.HttpPost;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;


//import org.apache.http.util.EntityUtils;

import java.util.EmptyStackException;


public class GetRestaurant extends Fragment {

    public String fetchRestaurants(String coordinates, int maxDistance) {
        // URL of the API endpoint
        String url = "http://localhost/phpcode/fypTest/api_postDetailRestaurant.php";

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