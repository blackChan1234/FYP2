package com.example.fyp;

public class TestGetRestaurant {

        public static void main(String[] args) {
            // Instance of the GetRestaurant class
            GetRestaurant getRestaurant = new GetRestaurant();

            // Example coordinates and maxDistance
            String coordinates = "{'lat': 40.7128, 'lon': -74.0060}"; // New York City coordinates, for example
            int maxDistance = 5000; // 5 kilometers

            // Fetch restaurants and print the result
            String result = getRestaurant.fetchRestaurants(coordinates, maxDistance);
            System.out.println(result);
        }

}
