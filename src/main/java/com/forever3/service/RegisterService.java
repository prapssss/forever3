package com.forever3.service;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;
 
/**
* RegisterService handles the registration of new users.
* It manages database interactions for user registration.
*/
public class RegisterService {
 
    private Connection dbConn;
 
    /**
     * Constructor initializes the database connection.
     */
    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /**
     * Registers a new user in the database.
     *
     * @param userModel the user details to be registered
     * @return Boolean indicating the success of the operation
     */
    public Boolean addUser(UserModel userModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return false;
        }
 
        String insertQuery = "INSERT INTO customer (first_name, last_name, user_name, number, email, password, image_url) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
 
        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            // Insert user details
            insertStmt.setString(1, userModel.getFirstName());
            insertStmt.setString(2, userModel.getLastName());
            insertStmt.setString(3, userModel.getUserName());
            insertStmt.setString(4, userModel.getNumber());
            insertStmt.setString(5, userModel.getEmail());
            insertStmt.setString(6, userModel.getPassword());
 
            // Handle nullable imageUrl
            if (userModel.getImageUrl() != null && !userModel.getImageUrl().isEmpty()) {
                insertStmt.setString(7, userModel.getImageUrl());
            } else {
                insertStmt.setNull(7, java.sql.Types.VARCHAR);
            }
 
            int rowsAffected = insertStmt.executeUpdate();
 
            if (rowsAffected > 0) {
                System.out.println("User registered successfully in database.");
                return true;
            } else {
                System.err.println("Insert failed. No rows affected.");
                return false;
            }
 
        } catch (SQLException e) {
            System.err.println("Error during customer registration: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
 