package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =  "jdbc:mysql://localhost:3306/swing_cloud_expediciones";
    private static final String USER = "root";
    private static final String PASS = "12345";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error conexión: " + e.getMessage());
            return null;
        }
    }
}