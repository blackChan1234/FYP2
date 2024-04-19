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


    @Override
    protected String doInBackground(String... params) {
        try {
            // 设置要发送 POST 请求的 URL
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 准备要发送的数据
            String postData = "vegan=false"; // 示例数据，请替换为实际数据

            // 发送数据
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

            // 返回响应数据
            return response.toString();
        } catch (IOException e) {
            Log.e("PostRequestTask", "Error sending POST request: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // 在这里处理从 PHP 脚本返回的结果
        if (result != null) {
            Log.d("PostRequestTask", "Received response: " + result);
            // 在这里处理返回的 JSON 数据
        } else {
            Log.e("PostRequestTask", "Failed to receive response");
        }
    }
}
