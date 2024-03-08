package com.example.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
public class MongoDBconnect {
    public static void main(String[] args) {
        // Assuming there's a MongoDB JDBC driver named `mongo.jdbc.MongoDriver`
        try {
            // Load the driver class
            Class.forName("mongo.jdbc.MongoDriver");

            // Create connection URL
            String url = "jdbc:mongo://host:port/database";

            // Establish a connection
            Connection conn = DriverManager.getConnection(url);

            // Perform database operations
            // ...

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MongoDB JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("MongoDB JDBC connection failed");
            e.printStackTrace();
        }
    }
}
