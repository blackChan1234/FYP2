package com.example.fyp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostRequestFoodByDatailTag extends AsyncTask<String, Void, String> {
    private String postData = "";
    private String result;

    public PostRequestFoodByDatailTag(String postData) {
        this.postData = postData;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            return response.toString();
        } catch (IOException e) {
            Log.e("PostRequestTask", "Error sending POST request: " + e.getMessage());
            return null;
        }
    }

    public String getResult() {
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        if (result != null) {
            Log.d("PostRequestTask", "Received response: " + result);
            Log.d("PostRequestTask", "length: " + result.length());

            this.result = result;


        } else {
            Log.e("PostRequestTask", "Failed to receive response");
            Log.e("PostRequestTask", postData);

        }
    }
}




